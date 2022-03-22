import { Link, useNavigate } from "react-router-dom";
import Footer from "../components/layout/Footer.jsx";
import Styles from "./Login.module.css";
import kakao from "../assets/kakao.png";
import { useState } from "react";
import axios from "axios";
import NewNav from "../components/layout/NewNav.jsx";

function Login() {

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
    // const LoginWithKakao = () => {
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
    //   };

    return (
        <div>
            {/* <Nav/> */}
            <NewNav />
            <div className={Styles.signin}>Sign in</div>
            <div className={Styles.loginbg}>
                <form>
                    <input type={"email"} placeholder=" E-mail" className={Styles.email} onChange={getEmail} name="email"/>
                    <input type={"password"} placeholder=" Password" className={Styles.password} onChange={getPassword} name="password"/>
                    <button className={Styles.signinbtn} onClick={onLogin}>Sign in</button>
                </form>
                <Link to="/Companylogin" className={Styles.company}>기업회원 로그인</Link>
                <div className={Styles.logindivjoin}></div>
                <div>
                    <div className={Styles.question}>Don’t have an account?</div>
                    <Link to="/join" className={Styles.joinusbtn} > Join us</Link>
                    <div className={Styles.or}> or </div>
                    <button className={Styles.kakaologin} ><img src={kakao} height={25} alt="kakaoTalk" style={{height: "2.5vh", marginTop:"1.5vh"}}></img> 카카오톡으로 로그인</button>
                </div>
            </div>


            {/* <Footer /> */}
        </div>
    );
}

export default Login;