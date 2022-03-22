import Styles from "./Join.module.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import Private from "../components/mypage/Private.jsx";
import NewNav from "../components/layout/NewNav.jsx";

function Join() {

    //개인정보처리방침 모달
    const [openPrivate, setOpenPrivate] = useState(false);

    //닉네임 받아오기
    const [nickname, setNickname] = useState("");
    const getNickname = (e) => {
        setNickname(e.target.value);
        console.log(nickname);
    };

    //닉네임 중복 체크
    const [checknick, setChecknick] = useState(false);
    

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
    const [phone, setPhone] = useState(false);
    const getPhone = (e) =>{
        //휴대폰인증 진행

        //휴대폰 인증 진행하고, 정상 처리되면 true로 바꾸기.
        setPhone(true);
    }

    //개인정보처리방침 동의
    const [pcheck, setPcheck] = useState(false);

    const getPcheck = (e) => {
        setPcheck(true);
        console.log(pcheck);
    }

    //회원가입
    const joinus = (e) => {
        if(nickname ===" " || email === " " || password === " "){
            e.preventDefault();
            alert("닉네임/이메일/비밀번호를 입력하세요.")
        } else if( phone === false){
            e.preventDefault();
            alert("휴대폰 인증을 진행해주세요.")
        } else if( pcheck===false ){
            e.preventDefault();
            alert("개인정보처리방침에 동의해주세요.")
        }
        else if( nickname !== " " && email !== " " && password !== " " && phone !== false && pcheck !== false ){

        }
    }

    return (
        <div>
            <NewNav />

            <div className={Styles.joinus}>Join us</div>
            <div className={Styles.joinbg}>
                <form>
                    <input type={"text"} placeholder="Nickname" name="nickname" onClick={getNickname} className={Styles.nickname} />
                    <input type={"email"} placeholder="E-mail" name="email" onClick={getEmail} className={Styles.email} />
                    <input type={"password"} placeholder="Password" name="password" onClick={getPassword} className={Styles.password} />
                    <button className={Styles.phonenum}>휴대폰 인증</button>
                    <input type={"checkbox"} className={Styles.privatecheck} onClick={getPcheck} />
                    <div className={Styles.privateset}>
                        <span>
                        {/* <input type={"checkbox"} className={Styles.privatecheck}></input> */}
                            <button className={Styles.privatebtn} onClick={() => {setOpenPrivate(true);}}>{" "}이용약관/개인정보처리방침{" "}</button> 
                            {openPrivate && (<Private closePrivate={setOpenPrivate}/>)}
                            {/* <h className={Styles.privatebtn}>이용약관/개인정보처리방침</h>  */}
                        {/* <h className={Styles.private}>에 동의합니다.</h> */}
                        <h className={Styles.private}>에 동의합니다.</h>
                        </span>
                    </div>
                    <button className={Styles.create} onClick={joinus}>CREATE ACCOUNT</button>
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