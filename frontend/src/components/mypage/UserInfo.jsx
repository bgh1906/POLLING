import { useEffect, useState } from "react";
import NewNav from "../layout/NewNav";
import Styles from "./UserInfo.module.css";
import { connect } from "react-redux";
import { actionCreators } from "../../store";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

function UserInfo({ state, DispatchdeleteInfo, DispatchmodifyNickname }) {
  const id = sessionStorage.getItem("userid");
  console.log("state", state);
  console.log("email", state[0].email);
  const token = sessionStorage.getItem("token");
  const nick = sessionStorage.getItem("nickname");
  const wallet = sessionStorage.getItem("wallet");
  console.log(nick);

  const logoutSuccess = () => {
    Swal.fire({
      title: "로그아웃!",
      // text: "POLLING을 이용해주셔서 감사합니다.",
      text: "오늘도 좋은 하루 보내세요",
      icon: "success",
      confirmButtonColor: "#73E0C1",
      confirmButtonText: "확인",
    });
  };

  const logoutFail = () => {
    Swal.fire({
      title: "로그아웃 실패",
      //   text: "POLLING을 이용해주셔서 감사합니다.",
      icon: "error",
      confirmButtonColor: "#73E0C1",
      confirmButtonText: "확인",
    });
  };

  const DeleteSuccess = () => {
    Swal.fire({
      title: "탈퇴 완료",
      text: "그동안 POLLING을 이용해주셔서 감사합니다.",
      icon: "success",
      confirmButtonColor: "#73E0C1",
      confirmButtonText: "확인",
    });
  };

  const DeleteFail = () => {
    Swal.fire({
      title: "탈퇴 실패",
      //   text: "POLLING을 이용해주셔서 감사합니다.",
      icon: "error",
      confirmButtonColor: "#73E0C1",
      confirmButtonText: "확인",
    });
  };

  const NickSuccess = () => {
    Swal.fire({
      title: "Nickname 변경 성공!",
      // text: "POLLING을 이용해주셔서 감사합니다.",
      icon: "success",
      confirmButtonColor: "#73E0C1",
      confirmButtonText: "확인",
    });
  };

  const NickFail = () => {
    Swal.fire({
      title: "동일 Nickname 존재",
      //   text: "POLLING을 이용해주셔서 감사합니다.",
      icon: "error",
      confirmButtonColor: "#73E0C1",
      confirmButtonText: "확인",
    });
  };

  const PassSuccess = () => {
    Swal.fire({
      title: "Password 변경 성공!",
      // text: "POLLING을 이용해주셔서 감사합니다.",
      icon: "success",
      confirmButtonColor: "#73E0C1",
      confirmButtonText: "확인",
    });
  };

  const PassFail = () => {
    Swal.fire({
      title: "Password 변경 실패",
      //   text: "POLLING을 이용해주셔서 감사합니다.",
      icon: "error",
      confirmButtonColor: "#73E0C1",
      confirmButtonText: "확인",
    });
  };

  //닉네임 받아오기
  const [nickname, setNickname] = useState("");
  const getNickname = (e) => {
    setNickname(e.target.value);
    console.log(e.target.value);
  };

  // const nick = state[0].nickname;
  

  useEffect(() => {}, [nick]);

  //닉네임 수정
  const getNickchange = () => {
    axios
      .patch(
        `https://j6a304.p.ssafy.io/api/members/nickname/${nickname}`,
        {
          nickname: nickname,
        },
        {
          headers: {
            Authorization: token,
            // refreshToken: token,
          },
        }
      )
      .then((res) => {
        DispatchmodifyNickname(nickname);
        sessionStorage.setItem("nickname", nickname);
        console.log("res", res);
        NickSuccess();
      })
      .catch((error) => {
        console.log("error", error);
        NickFail();
      });
  };

  //비번 받아오기
  const [password, setPassword] = useState("");
  const getPassword = (e) => {
    setPassword(e.target.value);
    console.log(e.target.value);
  };

  //비번 수정
  const getPasschange = () => {
    axios
      .patch(
        `https://j6a304.p.ssafy.io/api/members/password/${id}`,
        {
          password: password,
        },
        {
          headers: {
            Authorization: token,
            // refreshToken: token,
          },
        }
      )
      .then((res) => {
        console.log("res", res);
        PassSuccess();
      })
      .catch((error) => {
        console.log("error", error);
        PassFail();
      });
  };


  const navigation = useNavigate();
  //로그아웃
  const logout = () => {
    axios
      .get(
        "https://j6a304.p.ssafy.io/api/auth/logout",
        // "/api/auth/logout",
        // {},
        {
          headers: {
            // "Authorization":token,
            refreshToken: token,
          },
        }
      )
      .then((res) => {
        console.log("res", res);
        console.log("로그아웃");
        sessionStorage.clear();
        DispatchdeleteInfo();
        setNickname("");
        logoutSuccess();
        navigation("/");
      })
      .catch((error) => {
        console.log("error", error);
        logoutFail();
        console.log("로그아웃 실패");
      });
  };

  //탈퇴
  const getDelete = () => {
    axios
      .delete("https://j6a304.p.ssafy.io//api/members", {
        headers: {
          Authorization: token,
          // refreshToken: token,
        },
      })
      .then((res) => {
        console.log("res", res);
        console.log("탈퇴");
        sessionStorage.clear();
        DispatchdeleteInfo();
        DeleteSuccess();
        navigation("/");
      })
      .catch((error) => {
        console.log("error", error);
        DeleteFail();
        console.log("탈퇴 실패");
      });
  };

  //엔터로 작동하기
  const entermodifyN = (e) => {
    if (e.key === "Enter") {
      getNickchange();
    }
  };
  const entermodifyP = (e) => {
    if (e.key === "Enter") {
      getPasschange();
    }
  };

  return (
    <>
      {/* <h>Item One</h> */}
      <span className={Styles.textNickname}>Nickname : </span>
      {/* <input type={'text'} placeholder="" className={Styles.nickname} onChange={getNickname}></input> */}
      <label
        type={"text"}
        htmlFor="nick"
        className={Styles.nicknamelabel}
        onChange={getNickname}
        maxLength="12"
      >
        {nick}
      </label>
      <input
        type={"text"}
        value={nickname}
        to="nick"
        id="nick"
        className={Styles.nickname}
        onChange={getNickname}
        onKeyPress={entermodifyN}
      ></input>
      <button className={Styles.nicknamebtn} onClick={getNickchange}>
        수정
      </button>
      <div className={Styles.textEmail}>e-mail : </div>
      <div className={Styles.email}>{state[0].email}</div>
      <span className={Styles.textPassword}>Password : </span>
      <input
        type={"password"}
        placeholder="password"
        className={Styles.password}
        onChange={getPassword}
        onKeyPress={entermodifyP}
        maxLength="13"
      ></input>
      <button className={Styles.passwordbtn} onClick={getPasschange}>
        수정
      </button>
      <span className={Styles.textwallet}>Wallet : </span>
      <div
        type={"text"}
        placeholder="Wallet"
        className={Styles.wallet}
        style={{wordBreak:'break-all', lineHeight:'6vh'}}
      >{wallet}</div>
      {/* <button className={Styles.walletbtn} onClick={getnewWallet}>수정</button> */}
      {/* <br /> */}
      <button className={Styles.logout} onClick={logout}>
        로그아웃
      </button>
      <button className={Styles.out} onClick={getDelete}>
        탈퇴
      </button>
    </>
  );
}

function mapStateToProps(state) {
  return { state };
}
function mapDispatchToProps(dispatch) {
  return {
    DispatchdeleteInfo: () => dispatch(actionCreators.deleteInfo()),
    DispatchmodifyNickname: (nickname) =>
      dispatch(actionCreators.modifyNickname(nickname)),
  };
}
export default connect(mapStateToProps, mapDispatchToProps)(UserInfo);
