import { Link, useNavigate } from "react-router-dom";
import Footer from "../components/layout/Footer.jsx";
import Styles4 from "./Login4.module.css";
import kakao from "../assets/kakao.png";
import { useState } from "react";
import axios from "axios";
import NewNav from "../components/layout/NewNav.jsx";

function Login4() {

    //페이지 이동
    const [btn, setBtn] = useState('.img__btn');
    const change = (e) => {
        setBtn('.s__signup');
    };
    // document.querySelector('.imgbtn').addEventListener('click', function() {
    //     document.querySelector('.cont').classList.toggle('ssignup');
    //   });

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
            <p className={Styles4.tip}>Click on button in image container</p>
            <div className={Styles4.cont}>
            <div className={Styles4.form} class="sign-in">
                <div className={Styles4.sign_in}>
                    <h2>Welcome back,</h2>
                    <label className={Styles4.label}>
                        <span className={Styles4.email}>Email</span>
                        <input type="email" className={Styles4.input}/>
                    </label>
                    <label>
                        <span className={Styles4.password}>Password</span>
                        <input type="password" className={Styles4.input}/>
                    </label>
                    {/* <p class="forgot-pass">Forgot password?</p> */}
                    <button type="button" className={Styles4.submit} class="submit">Sign In</button>
                    <button type="button" className={Styles4.fb-btn} class="fb-btn">Connect with <span className={Styles4.kakao} >KakaoTalk</span></button>
                </div>
            </div>
            <div className={Styles4.sub_cont}  class="sub-cont">
                <div class="img" className={Styles4.img}>
                {/* <div class={Styles4.img__text} className={Styles4.m__up}>
                    <h2 className={Styles4.img__texth2}>New here?</h2>
                    <p className={Styles4.img__textp}>Sign up and discover great amount of new opportunities!</p>
                </div> */}
                {/* <div class={Styles4.img__text} className={Styles4.m__in}>
                    <h2 className={Styles4.img__texth2}>One of us?</h2>
                    <p className={Styles4.img__textp}>If you already has an account, just sign in. We've missed you!</p>
                </div> */}
                <div className={Styles4.img__btn}>
                    <span className={Styles4.spanm__up} onClick={change}>Sign Up</span>
                    <span className={Styles4.spanm__in}>Sign In</span>
                </div>
                </div>
                {/* <div class={Styles4.sign_up} className={Styles4.ssign_up}> */}
                <div className={Styles4.ssign_up}>
                <h2 className={Styles4.h2}>Time to feel like home,</h2>
                <label className={Styles4.label}>
                    <span className={Styles4.name}>Name</span>
                    <input type="text" className={Styles4.input}/>
                </label>
                <label>
                    <span className={Styles4.email}>Email</span>
                    <input type="email" className={Styles4.input}/>
                </label>
                <label>
                    <span className={Styles4.password}>Password</span>
                    <input type="password" className={Styles4.input}/>
                </label>
                <button type="button" className={Styles4.submit}>Sign Up</button>
                <button type="button" className={Styles4.fb-btn}>Join with <span className={Styles4.kakao}>KakaoTalk</span></button>
                </div>
            </div>
            </div>
        </div>
    );
}

export default Login4;