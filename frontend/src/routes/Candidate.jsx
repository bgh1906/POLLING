import NewNav from "../components/layout/NewNav.jsx";
import styles from "./Candidate.module.css";
import Button from "@mui/material/Button";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Comment from "../components/comment/Comment";
import mark from "../assets/mark_slim.png";
import crown from "../assets/crown.png";
import tx from "../assets/tx.png";
import tokenimg from "../assets/token.png";
import axios from "axios";
import Modal from "@mui/material/Modal";
import Swal from "sweetalert2";
import x from "../assets/x.png";
import stamp from "../assets/stamp.png";
import Lock from "../assets/Lock.png";
import {
  voteBlock,
  totalVotesBlock,
  unlockAccount,
  lockAccount,
  approveAccount,
  sendPOL,
  checkPOL,
} from "../contracts/CallContract";
import TextField from "@mui/material/TextField";
import { connect } from "react-redux";
import Txid1 from "./Txid1.jsx";
import { Co2Sharp } from "@mui/icons-material";

function Candidate({ state }) {
  const navigate = useNavigate();
  const params = useParams();

  const [candIdx, setCandIdx] = useState(0);
  const [candi_name, setCandi_name] = useState("");
  const [profile, setProfile] = useState("");
  const [profile_image, setProfile_image] = useState("");
  const [photo1, setPhoto1] = useState("");
  const [photo2, setPhoto2] = useState("");
  const [photo3, setPhoto3] = useState("");
  const [voteCount, setVoteCount] = useState(0);
  const [commentdata, setCommentdata] = useState([]);
  const [renderCount, setRenderCount] = useState(0);
  const [modalOpen, setmodalOpen] = useState(false);
  const [picked, setPicked] = useState(false);
  const [modalOpen2, setmodalOpen2] = useState(false);
  const [imageLock, setimageLock] = useState(true);
  const [modalOpen3, setmodalOpen3] = useState(false);
  const pollOpen = sessionStorage.getItem("open");
  const polltitle = sessionStorage.getItem("poll");
  const token = sessionStorage.getItem("token");
  const [inputWalletPw, setInputWalletPw] = useState("");
  const [inputImgPw, setInputImgtPw] = useState("");

  // ????????? ????????????
  const wallet = sessionStorage.getItem("wallet");
  useEffect(() => {
    window.scrollTo(0, 0);
    // {pollnum: '1', id: '5'}
  }, []);

  useEffect(() => {
    axios
      .get(`https://j6a304.p.ssafy.io/api/polls/candidates/${params.id}`)
      .then((res) => {
        setCandIdx(res.data.candidateIndex);
        setProfile_image(res.data.thumbnail);
        setPhoto1(res.data.imagePath1);
        setPhoto2(res.data.imagePath2);
        setPhoto3(res.data.imagePath3);
        setCandi_name(res.data.name);
        setProfile(res.data.profile);
        setCommentdata(res.data.comments);
        // "??? ????????? id:", params.id
        // "??? ????????? IDX:", res.data.candidateIndex
      })
      .catch((error) => {
        // console.log(error.response);
      });
  }, [renderCount]);

  getTotalVotes(candIdx);

  useEffect(() => {
    axios
      .get(`https://j6a304.p.ssafy.io/api/use-tokens/candidates/${params.id}`, {
        headers: {
          "Content-Type": "application/json",
          Authorization: token,
          Accept: "*/*",
        },
      })
      .then((res) => {
        const imagebuy = res.data[0];
        if (imagebuy) {
          setimageLock(false);
        } else {
          setimageLock(true);
        }
      })
      .catch((error) => {
        // console.log(error.response);
      });
  }, []);

  async function getTotalVotes(idx) {
    const totalVotes = await totalVotesBlock(idx, wallet);
    setVoteCount(totalVotes);
  }

  function changePhoto1() {
    setProfile_image(photo1);
    setPhoto1(profile_image);
  }

  function changePhoto2() {
    setProfile_image(photo2);
    setPhoto2(profile_image);
  }

  function changePhoto3() {
    setProfile_image(photo3);
    setPhoto3(profile_image);
  }

  function gotoList() {
    navigate(`/poll/${params.pollnum}`);
  }

  function renderCheck() {
    setRenderCount((renderCount) => renderCount + 1);
  }

  function handleOpen() {
    axios
      .get(
        `https://j6a304.p.ssafy.io/api/polls/candidates/limit/${params.id}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: token,
            Accept: "*/*",
          },
        }
      )
      .then((res) => {
        setmodalOpen(true);
      })
      .catch((error) => {
        // console.log("error", error.response);
        Swal.fire({
          title: "??? ????????? ??? ???????????? ????????? ??? ????????????.",
          icon: "error",
        });
      });
  }

  function handleClose() {
    setmodalOpen(false);
    setPicked(false);
    setInputWalletPw("");
  }

  function handlePicked() {
    setPicked((prev) => !prev);
  }

  const pollfin = () => {
    Swal.fire({
      title: "????????? ?????????????????????.",
      icon: "success",
    });
  };

  const adminAddress = "0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1";
  // const adminAddress = "0x0BcE168eb0fd21A6ae9bAD5C156bcC08633c2328";

  function getWalletPw(e) {
    setInputWalletPw(e.target.value);
  }

  const [reward, setReward] = useState(0);

  async function handlepoll() {
    if (picked && inputWalletPw !== "") {
      // ???????????? ?????? ?????? ??????
      //   1. Unlock ?????????.(???????????? ???????????????)
      unlockAccount(wallet, inputWalletPw);
      // 2. ??????????????? ??????????????? ????????????. & ????????? ???????????? ???????????? ????????????.

      const res = await voteBlock(candIdx, wallet);
      const txId = res.transactionHash;
      axios
        .post(
          `https://j6a304.p.ssafy.io/api/polls/candidates`,
          {
            candidateId: params.id,
            transactionId: txId,
            voteCount: 1,
          },
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: token,
              Accept: "*/*",
            },
          }
        )
        .then(async (res) => {
          //   ?????? ???????????? ????????? ????????? ???????????? ??????????????? ?????? state????????? ????????????
          renderCheck();
          lockAccount(wallet); //???????????? ?????? ??????
          pollfin(); //????????????
          handleClose(); //?????? ??????
          await approveAccount(100, adminAddress);
          await sendPOL(100, adminAddress, wallet);
          setReward((prev) => prev + 1);
        })
        .catch((error) => {
          // console.log("error", error.response);
        });
    } else if (picked && inputWalletPw === "") {
      Swal.fire({
        title: "?????? ??????????????? ???????????????.",
        icon: "error",
      });
    } else if (!picked && inputWalletPw !== "") {
      Swal.fire({
        title: "?????? ????????? ???????????????.",
        icon: "error",
      });
    } else {
      Swal.fire({
        title: "?????? ????????? ??????????????? ???????????????.",
        icon: "error",
      });
    }
  }

  function handleOpen2() {
    setmodalOpen2(true);
  }

  function handleClose2() {
    setmodalOpen2(false);
  }

  const imgopen = () => {
    Swal.fire({
      title: "????????? ?????? ???????????????.",
      icon: "success",
    });
  };

  const notoken = () => {
    Swal.fire({
      title: "????????? ???????????????.",
      icon: "error",
    });
  };
  const nopw = () => {
    Swal.fire({
      title: "?????? ??????????????? ???????????????.",
      icon: "error",
    });
  };

  function getImgPw(e) {
    setInputImgtPw(e.target.value);
  }

  const [tminus, setTminus] = useState(0);

  async function handleLock() {
    const balance = await checkPOL(wallet);
    if (balance >= 500 && inputImgPw !== "") {
      axios
        .post(
          "https://j6a304.p.ssafy.io/api/use-tokens/candidates",
          {
            candidateId: params.id,
          },
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: token,
              Accept: "*/*",
            },
          }
        )
        .then(async (res) => {
          unlockAccount(wallet, inputImgPw);
          await approveAccount(500, wallet);
          await sendPOL(500, wallet, adminAddress);
          imgopen(); //????????????
          handleClose3(); //?????? ??????
          setimageLock(false); //?????? ?????? ??????
          lockAccount(wallet); //lock????????? ?????????, ?????? ?????? ????????????
          setTminus((prev) => prev + 1); //????????? ?????????
        })
        .catch((error) => {
        });
    } else if (balance < 500 && inputImgPw !== "") {
      notoken();
    } else if (balance >= 500 && inputImgPw === "") {
      nopw();
    } else {
      Swal.fire({
        title: "?????? ?????? && ??????????????? ???????????????.",
        icon: "error",
      });
    }
  }

  function handleOpen3() {
    setmodalOpen3(true);
  }
  function handleClose3() {
    setmodalOpen3(false);
  }
  const rank = sessionStorage.getItem("rank");
  const listType = sessionStorage.getItem("listType");
  return (
    <>
      <NewNav reward={reward} tminus={tminus} />
      <div className={styles.container}>
        <img id={styles.crown} src={crown} alt="crown" />
        <img id={styles.tx} src={tx} alt="tx" />
        <span id={styles.name}>{candi_name}</span>
        <p id={styles.profile}>{profile}</p>
        <img
          id={styles.profile_image}
          src={profile_image}
          alt="profile_image"
        />
        {listType === "rank" && (
          <p id={styles.nowrank}> ?????? ??????: {rank}??? </p>
        )}
        {listType === "register" && (
          <p id={styles.nowrank}> ?????? No. {rank}??? </p>
        )}
        {pollOpen === "true" && (
          <p id={styles.nowpoll}>
            {" "}
            <img id={styles.mark} src={mark} alt={mark} />
            ?????? ?????????: {voteCount}???{" "}
          </p>
        )}
        {pollOpen === "false" && (
          <p id={styles.nowpoll}>
            {" "}
            <img id={styles.mark} src={mark} alt={mark} />
            ?????? ?????????:??????{" "}
          </p>
        )}
        <Button
          id={styles.poll_button}
          onClick={handleOpen}
          variant="contained"
        >
          ????????????
        </Button>
        <Modal open={modalOpen} onClose={handleClose}>
          <div id={styles.poll_paper}>
            <div id={styles.poll_paper2}>
              <img onClick={handleClose} id={styles.x_button} src={x} alt="x" />
              <p id={styles.poll_title}>{polltitle}</p>
              <p id={styles.paper_image}>
                <img
                  id={styles.paper_image}
                  src={profile_image}
                  alt="profile"
                ></img>
                {candi_name}
              </p>
              <p id={styles.stamp_box} onClick={handlePicked}>
                {picked ? (
                  <img id={styles.stamp} src={mark} alt="mark2" />
                ) : null}
              </p>
              <p id={styles.wallet_box}>
                <TextField
                  id={styles.wallet_password}
                  placeholder="Wallet Password"
                  variant="standard"
                  type="password"
                  onChange={getWalletPw}
                />
              </p>
              <p id={styles.paper_button}>
                <Button
                  onClick={handlepoll}
                  id={styles.paper_button2}
                  variant="contained"
                >
                  {" "}
                  ????????????
                </Button>
              </p>
              <p id={styles.stamp_box2}>
                <img id={styles.stamp2} src={stamp} alt="stamp" />
              </p>
              <p id={styles.paper_text}>
                ?????? ????????? ????????? ??? ?????? ???????????????.{" "}
              </p>
              <p id={styles.paper_text2}>??????????????? </p>
            </div>
          </div>
        </Modal>

        <Txid1 id={params.id} />

        <Button id={styles.back_button} onClick={gotoList} variant="contained">
          ????????? ??????
        </Button>
        <div id={styles.photobox}>
          {photo1 ? (
            <img
              id={styles.photo1}
              onClick={changePhoto1}
              src={photo1}
              alt="photo1"
            />
          ) : null}
          {photo2 ? (
            <img
              id={styles.photo2}
              onClick={changePhoto2}
              src={photo2}
              alt="photo2"
            />
          ) : null}

          {photo3 && imageLock ? (
            <img
              id={styles.photo3}
              onClick={handleOpen3}
              src={Lock}
              alt="Lock"
            />
          ) : null}

          <Modal open={modalOpen3} onClose={handleClose3}>
            <div id={styles.behind_box}>
              <img id={styles.behind_mark} src={mark} alt="mark" />
              <p id={styles.behind_marktext}>POLLING</p>
              <p id={styles.behind_text}>
                {" "}
                <img id={styles.tokenimg} src={tokenimg} alt="token" />
                500POL??? ???????????? <br />
                ????????? ????????? ???????????????????
              </p>
              <TextField
                className={styles.img_password}
                placeholder="Wallet Password"
                variant="standard"
                type="password"
                onChange={getImgPw}
              />
              <Button
                id={styles.behind_btn}
                onClick={handleLock}
                variant="contained"
              >
                {" "}
                ???{" "}
              </Button>
              <Button
                id={styles.behind_btn2}
                onClick={handleClose3}
                variant="contained"
              >
                {" "}
                ?????????{" "}
              </Button>
            </div>
          </Modal>

          {photo3 && imageLock === false ? (
            <img
              id={styles.photo3}
              onClick={changePhoto3}
              src={photo3}
              alt="photo3"
            />
          ) : null}
        </div>

        <Comment
          candiId={params.id}
          data={commentdata}
          renderCheck={renderCheck}
        ></Comment>
        <Button id={styles.list_button} onClick={gotoList} variant="contained">
          ???????????? ????????????
        </Button>
      </div>
    </>
  );
}

function mapStateToProps(state) {
  return { state };
}

export default connect(mapStateToProps, null)(Candidate);
