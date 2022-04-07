import * as React from "react";
import styles from "./History.module.css";

import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";

import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

function Historytxid({ pollId }) {
  const token = sessionStorage.getItem("token");
  const [open, setOpen] = React.useState(false);
  const navigate = useNavigate();
  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  //투표 내역문의 저장
  const [txidlist, setTxidlist] = React.useState([]);
  function needLogin() {
    Swal.fire({
      icon: "warning",
      title: "로그인 후 이용해주세요!",
      text: "확인을 누르면 로그인 페이지로 이동합니다.",
    });
    navigate("/login");
  }
  //투표 내역 받아오기
  React.useEffect(() => {
    // const getlist = () =>{
    axios
      .get(
        // /api/polls/candidates/polls/{pollsId}/{page}/{limit}
        `/api/polls/candidates/polls/${pollId}/0/50`,
        {
          headers: {
            Authorization: token,
            // refreshToken: token,
          },
        }
      )
      .then((res) => {
        console.log("data", res.data);
        setTxidlist(res.data);
      })
      .catch((error) => {
        console.log("res", error.response);
        console.log("error", error);
      });
  }, []);

  return (
    <div>
      <Button
        id={styles.button_info}
        variant="contained"
        size="large"
        onClick={() => {
          token === null ? needLogin() : handleClickOpen();
        }}
      >
        투표결과
      </Button>
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
        // style={{width:'20vw'}}
        maxWidth={"md"}
      >
        <DialogTitle
          id="alert-dialog-title"
          style={{ fontFamily: "ROKAFSansBold" }}
        >
          Transaction ID
        </DialogTitle>
        <DialogContent>
          <TableContainer component={Paper} style={{ width: "40vw" }}>
            <Table
              stickyHeader
              sx={{ minWidth: 400 }}
              aria-label="simple table"
            >
              <TableHead>
                <TableRow>
                  <TableCell
                    style={{ width: "8vw", fontFamily: "ROKAFSansBold" }}
                  >
                    Nickname
                  </TableCell>
                  <TableCell
                    style={{ width: "10vw", fontFamily: "ROKAFSansBold" }}
                  >
                    TransactionId
                  </TableCell>
                  <TableCell
                    style={{ width: "5vw", fontFamily: "ROKAFSansBold" }}
                  >
                    VoteCount&nbsp;
                  </TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {txidlist.map((row, key) => (
                  <TableRow
                    key={key}
                    sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                  >
                    {/* <TableCell style={{width:'8vw'}} component="th" scope="row"> */}
                    <TableCell style={{ width: "8vw" }}>
                      {row.nickname}
                    </TableCell>
                    <TableCell style={{ width: "10vw" }}>
                      {row.transactionId.slice(0, 39)}...
                      {/* {row.transactionId} */}
                    </TableCell>
                    <TableCell style={{ width: "5vw" }}>
                      {row.voteCount}
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </DialogContent>
        <DialogActions>
          <Button
            onClick={handleClose}
            autoFocus
            style={{ fontFamily: "ROKAFSansBold" }}
          >
            close
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
export default Historytxid;
