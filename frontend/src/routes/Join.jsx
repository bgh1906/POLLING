import Nav from "../components/layout/Nav.jsx"
import Styles from "./Join.module.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

function Join() {

    //개인정보처리방침 모달

    //닉네임 받아오기
    const [nickname, setNickname] = useState("");
    const getNickname = (e) => {
        setNickname(e.target.value);
        console.log(nickname);
    };

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

    //휴대폰인증
    const phone = (e) =>{
        
    }

    //회원가입
    const joinus = (e) => {

    }

    return (
        <div>
            <Nav />

            <div className={Styles.joinus}>Join us</div>
            <div className={Styles.joinbg}>
                <form>
                    <input type={"text"} placeholder="Nickname" name="nickname" onClick={getNickname} className={Styles.nickname} />
                    <input type={"email"} placeholder="E-mail" name="email" onClick={getEmail} className={Styles.email} />
                    <input type={"password"} placeholder="Password" name="password" onClick={getPassword} className={Styles.password} />
                    <button className={Styles.phonenum}>휴대폰 인증</button>
                    <input type={"checkbox"} className={Styles.privatecheck} />
                    <div className={Styles.privateset}>
                        <span>
                        {/* <input type={"checkbox"} className={Styles.privatecheck}></input> */}
                            <button className={Styles.privatebtn}>이용약관/개인정보처리방침</button> 
                            {/* <h className={Styles.privatebtn}>이용약관/개인정보처리방침</h>  */}
                        {/* <h className={Styles.private}>에 동의합니다.</h> */}
                        <h className={Styles.private}>에 동의합니다.</h>
                        </span>
                    </div>
                    <button className={Styles.create}>CREATE ACCOUNT</button>
                </form>
                <div>
                    <h className={Styles.signinQ}>Alraedy have an account?</h>
                    <Link to="/login" className={Styles.signin}>Sign in</Link>
                </div>
            </div>

            {/* <Footer /> */}
        </div>
    );
}

export default Join;