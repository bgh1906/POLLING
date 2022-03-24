import { Link, useNavigate } from "react-router-dom";
import Footer from "../components/layout/Footer.jsx";
import Styles2 from "./Login2.module.css";
import kakao from "../assets/kakao.png";
import { useState } from "react";
import axios from "axios";
import NewNav from "../components/layout/NewNav.jsx";

function Login2() {

    //로그인/회원가입 체인지
    // const [loginChange, setLoginChange] = useState();



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
            {/* 로그인 */}
            <div className={Styles2.container} onclick="onclick">
                <div className={Styles2.top}></div>
                <div className={Styles2.bottom}></div>
                <div className={Styles2.center}>
                    <div className={Styles2.signin}>Sign In&nbsp;</div>
                    {/* <h2>Sign In &nbsp;</h2>  */}
                    {/* <input type="email" placeholder="email"/>
                    <input type="password" placeholder="password"/> */}
                    <form>
                        <input type={"email"} placeholder=" E-mail" className={Styles2.email} onChange={getEmail} name="email"/>
                        <input type={"password"} placeholder=" Password" className={Styles2.password} onChange={getPassword} name="password"/>
                        <button className={Styles2.signinbtn} onClick={onLogin}>Sign in</button>
                    </form>
                    <div>
                        <Link to="/Companylogin" className={Styles2.company}>기업회원 로그인 / </Link>
                        {/* <button className={Styles2.kakao}>
                            <img src={kakao}  alt="kakaoTalk" className={Styles2.kakaoimg}></img>
                        </button> */}
                        <button className={Styles2.kakao} onClick={LoginWithKakao}> 카카오톡 로그인 </button>
                    </div>
                    <div className={Styles2.question}>Don’t have an account?</div>
                    <Link to="/join" className={Styles2.join}>Join us</Link>
                    <h2>&nbsp;</h2>
                </div>
            </div>

            {/* <Footer /> */}
        </div>
    );
}

export default Login2;