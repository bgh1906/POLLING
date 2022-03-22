import Styles2 from "./Join2.module.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import Private from "../components/mypage/Private.jsx";
import NewNav from "../components/layout/NewNav.jsx";

function Join2() {

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
            <div className={Styles2.container} onclick="onclick">
                <div className={Styles2.top}></div>
                <div className={Styles2.bottom}></div>
                <div className={Styles2.center}>
                        {/* <div className={Styles.joinbg}> */}
                        <div className={Styles2.joinus}>Join us&nbsp;</div>
                        {/* <h2>Join us &nbsp;</h2>  */}
                        {/* <input type="email" placeholder="email"/>
                        <input type="password" placeholder="password"/> */}
                        <form>
                            <input type={"text"} placeholder="Nickname" name="nickname" onClick={getNickname} className={Styles2.nickname} />
                            <input type={"email"} placeholder=" E-mail" className={Styles2.email} onChange={getEmail} name="email"/>
                            <input type={"password"} placeholder=" Password" className={Styles2.password} onChange={getPassword} name="password"/>
                            <button className={Styles2.phonenum}>휴대폰 인증</button>
                            <input type={"checkbox"} className={Styles2.privatecheck} onClick={getPcheck} />
                            <div className={Styles2.privateset}>
                                <span>
                                {/* <input type={"checkbox"} className={Styles.privatecheck}></input> */}
                                    <button className={Styles2.privatebtn} onClick={() => {setOpenPrivate(true);}}>{" "}이용약관/개인정보처리방침{" "}</button> 
                                    {openPrivate && (<Private closePrivate={setOpenPrivate}/>)}
                                    {/* <h className={Styles.privatebtn}>이용약관/개인정보처리방침</h>  */}
                                {/* <h className={Styles.private}>에 동의합니다.</h> */}
                                <h className={Styles2.private}> 동의</h>
                                </span>
                            </div>
                            {/* <button className={Styles2.signinbtn} onClick={onLogin}>Sign in</button> */}
                            <button className={Styles2.create} onClick={joinus}>CREATE ACCOUNT</button>
                        </form>
                        <div>
                            {/* <h className={Styles2.signinQ}>Alraedy have an account?</h> */}
                            <Link to="/login" className={Styles2.signinQ}>Alraedy have an account?</Link>
                            <Link to="/login" className={Styles2.signin}>Sign in</Link>
                        </div>
                    {/* </div> */}
                </div>
            </div>
            {/* <Footer /> */}
        </div>
    );
}

export default Join2;