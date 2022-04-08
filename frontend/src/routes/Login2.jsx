import { Link, useNavigate } from "react-router-dom";
import Styles2 from "./Login2.module.css";
import React, { useState } from "react";
import axios from "axios";
import NewNav from "../components/layout/NewNav.jsx";
import Swal from "sweetalert2";
import { actionCreators } from "../store";
import Kakaojoin from "./Kakaojoin.jsx"; //사용합니다
import { useDispatch } from "react-redux";

const { Kakao } = window;

function Login2() {
  React.useEffect(() => {
    setEmail("");
    setPassword("");
  }, []);

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

  //이메일 받아오기
  const [email, setEmail] = useState("");
  const getEmail = (e) => {
    setEmail(e.target.value);
  };

  //비밀번호 받아오기
  const [password, setPassword] = useState("");
  const getPassword = (e) => {
    setPassword(e.target.value);
  };
  //빈칸확인
  const inputnull = () => {
    Swal.fire({
      text: "이메일/비밀번호를 입력하세요",
      icon: "error",
      confirmButtonColor: "#73E0C1",
      confirmButtonText: "확인",
    });
  };

  //페이지 이동
  const navigate = useNavigate();

  // 회원가입 로그인하기
  const onLogin = (e) => {
    if (email === "" || password === "") {
      e.preventDefault();
      inputnull();
    } else if (email !== "" && password !== "") {
      axios
        .post("https://j6a304.p.ssafy.io/api/auth", {
          email: email,
          password: password,
        })
        .then((res) => {
          sessionStorage.setItem("token", res.headers.refreshtoken);
          sessionStorage.setItem("userid", res.data.id);
          sessionStorage.setItem("role", res.data.role);
          sessionStorage.setItem("nickname", res.data.nickname);
          sessionStorage.setItem("wallet", res.data.wallet);

          dispatch(
            actionCreators.addInfo({
              id: res.data.id,
              nickname: res.data.nickname,
              token: res.headers.refreshtoken,
              email: email,
              wallet: res.data.wallet,
            })
          );
          loginSuccess();
          navigate("/");
        })
        .catch((error) => {
          const message = error.message;
          // console.log("error", error);
          loginFail();
        });
    }
  };

  //카톡 로그인
  const LoginWithKakao = () => {
    Kakao.Auth.login({
      success: (response) => {
        const accessToken = response.access_token;
        axios
          .post("https://j6a304.p.ssafy.io/api/auth/validate", {
            accessToken: response.access_token,
          })
          .then((res) => {
            // const token
            console.log("wallet", res.data.wallet);

            if (res.data.existMember === false) {
              dispatch(
                actionCreators.addInfo({
                  email: email,
                })
              );
              navigate(`/Kakaojoin/${accessToken}`);
            } else if (res.data.existMember === true) {
              sessionStorage.setItem("token", accessToken);
              sessionStorage.setItem("userid", res.data.id);
              sessionStorage.setItem("role", res.data.role);
              sessionStorage.setItem("wallet", res.data.wallet);
              sessionStorage.setItem("nickname", res.data.nickname);
              dispatch(
                actionCreators.addInfo({
                  token: response.access_token,
                  email: "카카오톡 로그인 회원",
                  id: res.data.id,
                  nickname: res.data.nickname,
                  wallet: res.data.wallet,
                })
              );
              loginSuccess();
              navigate("/");
            }
          });
      },
      fail: (error) => {
        const message = error.message;
        // console.log("error", error);
        loginFail();
        navigate("/");
      },
    });
  };

  return (
    <>
      <div>
        <NewNav />
        {/* 로그인 */}
        <div className={Styles2.container}>
          <div className={Styles2.top}></div>
          <div className={Styles2.bottom}></div>
          <div className={Styles2.center}>
            <div className={Styles2.signin}>Log In&nbsp;</div>
            <input
              type={"email"}
              placeholder=" E-mail"
              className={Styles2.email}
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
              로그인
            </button>
            <div>
              <Link to="/Companylogin" className={Styles2.company}>
                기업회원 로그인 /{" "}
              </Link>
              <button className={Styles2.kakao} onClick={LoginWithKakao}>
                {" "}
                카카오톡 로그인{" "}
              </button>
            </div>
            <div className={Styles2.question}>Don’t have an account?</div>
            <Link to="/join" className={Styles2.join}>
              Join us
            </Link>
            <h2>&nbsp;</h2>
          </div>
        </div>
      </div>
    </>
  );
}

export default Login2;
