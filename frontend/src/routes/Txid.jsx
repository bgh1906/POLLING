import * as React from 'react';
import styles from "./Candidate.module.css";

import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import axios from 'axios';

function Txid({id}) {
    function createData(name, calories, fat, carbs, protein) {
        return { name, calories, fat, carbs, protein };
    }
    
    const rows = [
        createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
        createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
        createData('Eclair', 262, 16.0, 24, 6.0),
        createData('Cupcake', 305, 3.7, 67, 4.3),
        createData('Gingerbread', 356, 16.0, 49, 3.9),
    ];

    const token = sessionStorage.getItem("token")
   const [open, setOpen] = React.useState(false);

   const handleClickOpen = () => {
     setOpen(true);
   };

   const handleClose = () => {
     setOpen(false);
   };

  //투표 내역문의 저장
  const [txidlist, setTxidlist] = React.useState([]);

    //투표 내역 받아오기
    React.useEffect(() => {
    // const getlist = () =>{
    let iscompomount = true;

    if( iscompomount === true ){
      axios
      .get(
        // `https://j6a304.p.ssafy.io/api/polls/candidates/members/${page}/${limit}`,
        `/api/polls/candidates/polls/${id}/0/50`,
        {
          headers: {
            "Authorization":token,
            // refreshToken: token,
          },
        }
      )
      .then((res) => {
        // console.log("data",res.data);
        setTxidlist(res.data);
      })
      .catch(error => {
        console.log("res",error.response);
        console.log("error",error);
      })
    };
    return() => {
      iscompomount = false;
    };
  },[]);
  

  return (
    <div>
      <Button
          id={styles.con_button}
          onClick={handleClickOpen}
          variant="contained"
        >
          투표내역
    </Button>
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
        // style={{width:'20vw'}}
        maxWidth={'md'}
      >
        <DialogTitle id="alert-dialog-title" style={{fontFamily:'ROKAFSansBold'}}>
            Transaction ID
        </DialogTitle>
        <DialogContent>
        <TableContainer component={Paper} style={{width:'40vw'}}>
          <Table stickyHeader sx={{ minWidth: 400 }} aria-label="simple table">
            <TableHead >
              <TableRow>
                <TableCell style={{width:'8vw',fontFamily:'ROKAFSansBold'}}>nickname</TableCell>
                <TableCell style={{width:'10vw',fontFamily:'ROKAFSansBold'}}>transactionId</TableCell>
                <TableCell style={{width:'5vw',fontFamily:'ROKAFSansBold'}}>voteCount&nbsp;</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {txidlist.map((row) => (
                <TableRow
                  key={row.nickname}
                  sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                  {/* <TableCell style={{width:'8vw'}} component="th" scope="row"> */}
                  <TableCell style={{width:'8vw'}}>
                    {row.name}
                  </TableCell>
                  <TableCell style={{width:'10vw'}}>{row.transactionId}</TableCell>
                  <TableCell style={{width:'5vw'}}>{row.voteCount}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Disagree</Button>
          <Button onClick={handleClose} autoFocus>
            Agree
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
export default Txid;