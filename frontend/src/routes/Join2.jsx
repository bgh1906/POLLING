import Styles2 from "./Join2.module.css";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import NewNav from "../components/layout/NewNav.jsx";

import * as React from 'react';

import Private2 from "../components/mypage/Private2";

import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

import Swal from "sweetalert2";

function Join2() {

    //리로딩할 때마다 값 리셋
    React.useEffect(() => {
        setNickname("");
        setEmail("");
        setPassword("");
        setPhone("");
        setPhonecheck(false);
        setPcheck(false);
    }, []);
    
    //alert 창
    const joinSuccess = () => {
        Swal.fire({
          title: "회원가입 성공!!",
          text: "POLLING에 오신 것을 환영합니다!",
          icon: "success",
          confirmButtonColor: "#73E0C1",
          confirmButtonText: "확인",
        })
      };
    
      const joinFail = () => {
        Swal.fire({
          title:"회원가입 실패!",
          icon: 'error',
          confirmButtonColor: '#73E0C1',
          confirmButtonText: '확인'
        })
      }

    //닉네임 받아오기
    const [nickname, setNickname] = useState("");
    const getNickname = (e) => {
        setNickname(e.target.value);
        console.log(nickname);
    };

    //닉네임 중복 체크
    const [checknick, setChecknick] = useState(false);
    
    const getChecknick = (e) => {
        if(nickname === "") {
            alert("Nickname을 입력해주세요.")
        } else {
            axios
            .get(
                `https://j6a304.p.ssafy.io:8080/api/members/nickname/${nickname}`,
                // `http://j6a304.p.ssafy.io:8080/api/members/nickname/${nickname}`,
                {
                    // nickname: nickname,
                    n: nickname,
                    // n:n
                }           
            )
            .then((res) => {
                console.log("res", res);
                alert("사용가능한 닉네임입니다.");
                setChecknick(true);
            })
            .catch(error => {
                console.log("error", error.response);
                alert("동일 닉네임이 존재합니다.");
                setNickname("");
            })
            console.log("nickname",nickname);
        }
    }

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

    //휴대전화 번호 입력
    const [phone, setPhone] = useState("");
    const getPhonenum = (e) =>{
        setPhone(e.target.value);
        console.log(phone);
    }

    //휴대폰인증 모달창
    const [open, setOpen] = React.useState(false);
    //인증번호 버튼 잠그기
    const [phonelock, setPhonelock] = useState(false);
    //찐 인증번호 확인위해 저장하기
    const [realNum, setRealNum] = useState("");

    //인증창 열기 & 인증번호 보내기
    const handleClickOpen = (e) => {
        //인증번호 보내기
        if(phone === ""){
            e.preventDefault();
            alert("휴대폰 번호를 입력해주세요")
        }
        else if(phone !== "") {
            axios
            .post(
                "https://j6a304.p.ssafy.io:8080/api/notify/sms",
                {
                    content : "",
                    to : phone,
                },
            )
            .then((res) => {
                console.log("인증번호 발송", res);
                alert("인증번호가 전송되었습니다!")
                setOpen(true);
                setPhonelock(true);
                setRealNum(res.data.code);
                console.log(res.data.code);
            })
            .catch(error => {
                const message = error.message;
                console.log("message",error.response)
                console.log("message", message);
                alert("인증번호 전송 실패!");
            }); 
        }
    };
    //인증창 닫기
    const handleClose = () => {
        setOpen(false);
    };
    //인증번호 입력 태그랑, 인증확인보낼 태크 만들기 -> 인증번호 받으면 버튼 비활성화 시키기
    //입력받은 인증번호 값
    const [checknum, setChecknum] = useState("");
    const getChecknum = (e) => {
        setChecknum(e.target.value);
        console.log(checknum);
    }
    //인증번호 맞는지 체크
    const [phonecheck, setPhonecheck] = useState(false);
    const getPhone = (e) =>{
        //휴대폰인증 진행
        if(checknum === " "){
            e.preventDefault();
            alert("인증번호를 입력해주세요")
        }
        else if(checknum !== "") {
            if(checknum !== realNum ){
                alert("인증번호가 틀렸습니다.")
            }
            else if(checknum === realNum ){
                alert("본인 확인 완료")
                //정상 처리되면 true로 바꾸기 & 모달 종료
                setPhonecheck(true);
                setOpen(false);
            }
        }
    }

    //개인정보처리방침 동의 여부
    const [pcheck, setPcheck] = useState(false);

    const getPcheck = (e) => {
        setPcheck(!pcheck);
        console.log(pcheck);
    }

    //페이지 이동
    const navigate = useNavigate();

    //회원가입
    const joinus = (e) => {
        if(nickname ===" " || email === " " || password === " "){
            e.preventDefault();
            alert("닉네임/이메일/비밀번호를 입력하세요.")
        } 
        else if( phonecheck === false){
            e.preventDefault();
            alert("휴대폰 인증을 진행해주세요.")
        } 
        else if( checknick === false){
            e.preventDefault();
            alert("닉네임 중복체크를 진행해주세요.")
        } 
        else if( pcheck===false ){
            e.preventDefault();
            alert("개인정보처리방침에 동의해주세요.")
        }
        else if( nickname !== " " && email !== " " && password !== " " && phone !== " " && phonecheck !== false &&checknick !== false && pcheck !== false ){
        // else if( nickname !== " " && email !== " " && password !== " " && phone !== " " && pcheck !== false ){
            axios
            .post(
                "https://j6a304.p.ssafy.io:8080/api/members",
                {
                    email: email,
                    nickname: nickname,
                    password: password,
                    phoneNumber: phone,
                    role: "ROLE_USER",
                },
            )
            .then((res) => {
                console.log("res", res);
                joinSuccess();
                console.log("회원가입")
                navigate("/login");
            })
            .catch(error => {
                const message = error.message;
                console.log("message", message);
                console.log(error.response);
                joinFail();
            });
        }
    }
        
        return (
            <div>
            <NewNav />
            {/* <div className={Styles2.container} onclick="onclick"> */}
            <div className={Styles2.container} >
                <div className={Styles2.top}></div>
                <div className={Styles2.bottom}></div>
                <div className={Styles2.center}>
                        {/* <div className={Styles.joinbg}> */}
                        <div className={Styles2.joinus}>Join us&nbsp;</div>
                        {/* <h2>Join us &nbsp;</h2>  */}
                        {/* <input type="email" placeholder="email"/>
                        <input type="password" placeholder="password"/> */}
                            <input type={"text"} placeholder="Nickname" className={Styles2.nickname} name="nickname" onChange={getNickname}  />
                            <button className={Styles2.nicknameCheck} onClick={getChecknick} disabled={checknick === true}>중복확인</button>
                            <input type={"email"} placeholder=" E-mail" className={Styles2.email} onChange={getEmail} name="email"/>
                            <input type={"password"} placeholder=" Password" className={Styles2.password} onChange={getPassword} name="password"/>
                            <input type={"text"} placeholder="PhoneNumber" className={Styles2.phonenum} onChange={getPhonenum} name="phone"/>
                            <button className={Styles2.phonebtn} onClick={handleClickOpen} disabled={phonelock === true}>본인인증</button>
                            <Dialog open={open} onClose={handleClose}>
                                <DialogTitle className={Styles2.phonetitle}>휴대폰 인증</DialogTitle>
                                <DialogContent>
                                <DialogContentText className={Styles2.phonetitle}>
                                    입력하신 번호로 받으신 인증번호를 입력해주세요.
                                </DialogContentText>
                                <TextField
                                    autoFocus
                                    margin="dense"
                                    id="num"
                                    label="Verification Code"
                                    type="text"
                                    fullWidth
                                    variant="standard"
                                    className={Styles2.phonetitle}
                                    onChange={getChecknum}
                                />
                                </DialogContent>
                                <DialogActions>
                                <button onClick={handleClose} className={Styles2.phonemodalclose}>Cancel</button>
                                <button onClick={getPhone} className={Styles2.phonemodalSubscribe}>Subscribe</button>
                                </DialogActions>
                            </Dialog>
                            
                            <input type={"checkbox"} className={Styles2.privatecheck} onClick={getPcheck} />
                            <div className={Styles2.privateset}>
                                <span>
                                {/* <input type={"checkbox"} className={Styles.privatecheck}></input> */}
                                    {/* <button className={Styles2.privatebtn} onClick={getOpenPrivate}>{" "}이용약관/개인정보처리방침{" "}</button>  */}
                                    {/* <button className={Styles2.privatebtn} variant="outlined" onClick={getOpenPrivate}>{" "}이용약관/개인정보처리방침{" "}</button>  */}
                                    {/* {openPrivate && (<Private closePrivate={getClosePrivate}/>)} */}
                                    {/* <Private /> */}
                                    <Private2 />
                                <p className={Styles2.private}> 동의</p>
                                </span>
                            </div>
                            {/* <button className={Styles2.signinbtn} onClick={onLogin}>Sign in</button> */}
                            <button className={Styles2.create} onClick={joinus}>CREATE ACCOUNT</button>
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