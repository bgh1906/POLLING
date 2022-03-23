import { Link, useNavigate } from "react-router-dom";
import Footer from "../components/layout/Footer.jsx";
import Styles3 from "./Login3.module.css";
import kakao from "../assets/kakao.png";
import { useState } from "react";
import axios from "axios";
import NewNav from "../components/layout/NewNav.jsx";

function Login3() {

    //이메일 받아오기
    const [email, setEmail] = useState("");
    const getEmail = (e) => {
        setEmail(e.target.value);
        console.log(email);
    };

    //비밀번호 받아오기
    const [password, setPassword] = useState("");
    const getPassword = (e) => {
        setPassword(e.target.value);
        console.log(password);
    };

    //페이지 이동
    const navigate = useNavigate();

    // 회원가입 로그인하기
    const onLogin = (e) => {
        if(email === "" || password === ""){
            e.preventDefault();
            alert("이메일/비밀번호를 입력해주세요");
        } else if(email !== "" && password !==""){
    //         axios
    //           .post(
    //               "",
    //               {
    //                   email: email,
    //                   password: password,
    //               },
    //           )
    //         .then((res) => {
    //            navigate("/");
    //         })
            alert("로그인");
        }
    };

    //카톡 로그인
    const LoginWithKakao = () => {
        alert("kakao login");
    //     Kakao.Auth.login({
    //       success: (response) => {
    //         axios
    //           .post("", {
    //             accessToken: response.access_token,
    //             oauthType: "KAKAO",
    //             refreshToken: response.refresh_token,
    //           })
    //           .then((res) => {
    //             localStorage.setItem("token", res.data.token);
    //             if (state.length === 0) {
    //               DispatchaddInfo({
    //                 seq: res.data.seq,
    //                 nickname: res.data.nickname,
    //                 token: res.data.token,
    //                 email: res.data.email,
    //               });
    //             }
    //             loginSuccess();
    //             navigate("/");
    //           });
    //       },
    //       fail: (error) => {
    //         loginFail();
    //         navigate("/");
    //       },
    //     });
      };

    return (
        <div>
            {/* <Nav/> */}
            <NewNav />
                <div className={Styles3.signin}>Sign In</div>
            <div className={Styles3.login}>
                <form >
                    <input type={"email"} placeholder=" E-mail" className={Styles3.email} onChange={getEmail} name="email"/>
                    <input type={"password"} placeholder=" Password" className={Styles3.password} onChange={getPassword} name="password"/>
                    <button className={Styles3.signinbtn} onClick={onLogin}>Sign in</button>
                </form>
                <Link to="/Companylogin" className={Styles3.company}>기업회원 로그인</Link>
                <div className={Styles3.logindivjoin}></div>
                <div>
                    <div className={Styles3.question}>Don’t have an account?</div>
                    <Link to="/join" className={Styles3.joinusbtn} > Join us</Link>
                    <div className={Styles3.or}> or </div>
                    <button className={Styles3.kakaologin} onClick={LoginWithKakao}><img src={kakao} height={25} alt="kakaoTalk" style={{height: "2.5vh" , paddingTop:"0.5vh"}}></img> 카카오톡 로그인</button>
                </div>
                <h2>&nbsp;</h2>
            </div>
        </div>
    );
}

export default Login3;