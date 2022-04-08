import { Link, useNavigate } from "react-router-dom";
import Styles2 from "./Companylogin2.module.css";
import { useState } from "react";
import axios from "axios";
import NewNav from "../components/layout/NewNav.jsx";
import { actionCreators } from "../store";
import { useDispatch } from "react-redux";
import Swal from "sweetalert2";

function Companylogin2() {
  const dispatch = useDispatch();
  const loginSuccess = () => {
    Swal.fire({
      title: "로그인 성공!",
      text: "POLLING에 오신 것을 환영합니다!",
      icon: "success",
      confirmButtonColor: "#73E0C1",
      confirmButtonText: "확인",
    });
  };

  const loginFail = () => {
    Swal.fire({
      title: "로그인 실패!",
      icon: "error",
      confirmButtonColor: "#73E0C1",
      confirmButtonText: "확인",
    });
  };

  //email 받아오기
  const [email, setEmail] = useState("");
  const getEmail = (e) => {
    setEmail(e.target.value);
  };

  //비밀번호 받아오기
  const [password, setPassword] = useState("");
  const getPassword = (e) => {
    setPassword(e.target.value);
  };

  //페이지 이동
  const navigate = useNavigate();

  // 로그인하기
  const onLogin = (e) => {
    if (email === "" || password === "") {
      e.preventDefault();
      Swal.fire({
        title: "이메일/비밀번호를 입력해주세요",
        icon: "error",
        confirmButtonColor: "#73E0C1",
        confirmButtonText: "확인",
      });
    } else if (email !== "" && password !== "") {
      axios
        .post("http://j6a304.p.ssafy.io/api/auth", {
          email: email,
          password: password,
        })
        .then((res) => {
          sessionStorage.setItem("token", res.headers.refreshtoken);
          sessionStorage.setItem("userid", res.data.id);
          sessionStorage.setItem("role", res.data.role);
          sessionStorage.setItem("wallet", res.data.wallet);
          dispatch(
            actionCreators.addInfo({
              token: res.headers.refreshtoken,
              email: email,
              id: res.data.id,
              nickname: res.data.nickname,
              wallet: res.data.wallet,
            })
          );
          loginSuccess();
          navigate("/");
        })
        .catch((error) => {
          const message = error.message;
          loginFail();
        });
    }
  };

  return (
    <div>
      <NewNav />
      <div className={Styles2.container}>
        <div className={Styles2.top}></div>
        <div className={Styles2.bottom}></div>
        <div className={Styles2.center}>
          <div className={Styles2.signin}>Sign in_Business</div>
          <input
            type="text"
            placeholder=" Business E-mail"
            className={Styles2.id}
            onChange={getEmail}
            name="email"
          />
          <input
            type={"password"}
            placeholder=" Password"
            className={Styles2.password}
            onChange={getPassword}
            name="password"
          />
          <button className={Styles2.signinbtn} onClick={onLogin}>
            Sign in
          </button>
          <Link to="/login" className={Styles2.login}>
            일반회원이신가요?
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Companylogin2;
