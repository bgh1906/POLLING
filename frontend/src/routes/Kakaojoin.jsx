import Styles from "./Kakaojoin.module.css";
import NewNav from "../components/layout/NewNav";
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import * as React from 'react';
import axios from "axios";
import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import Private2 from "../components/mypage/Private2";
import { connect } from "react-redux";
import { actionCreators } from "../store";
import { useDispatch } from 'react-redux'

function Kakaojoin({ DispatchdeleteInfo }) {

    React.useEffect(() => {
        setNickname("");
        setPhone("");
      }, []);

    const params = useParams();

    // console.log(params.accessToken);
    const dispatch = useDispatch();

    //alert 창
    const joinSuccess = () => {
        Swal.fire({
          title: "회원가입 성공!!",
          text: "Polling에 오신 것을 환영합니다!",
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
                `https://j6a304.p.ssafy.io/api/members/nickname/${nickname}`,
                // `https://j6a304.p.ssafy.io:8080/api/members/nickname/${nickname}`,
                {
                    n: nickname,
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
                "https://j6a304.p.ssafy.io/api/notify/sms",
                // "https://j6a304.p.ssafy.io:8080/api/notify/sms",
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
        if(nickname ===" "){
            e.preventDefault();
            alert("닉네임을 입력하세요.")
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
        else if( nickname !== " " && phone !== " " && phonecheck !== false &&checknick !== false && pcheck !== false ){
        // else if( nickname !== " " && email !== " " && password !== " " && phone !== " " && pcheck !== false ){      
            axios
            .post("https://j6a304.p.ssafy.io/api/auth/social", {
            // .post("https://j6a304.p.ssafy.io:8080/api/auth/social", {
                // accessToken: response.access_token,
                accessToken: params.accessToken,
                nickname: nickname,
                phoneNumber: phone,
            })
            .then((res) => {
                console.log("res",res);
                // localStorage.setItem("token", res.data.token);
                          
                // if (state.length === 0) {
                //   DispatchaddInfo({
                //     seq: res.data.seq,
                //     nickname: res.data.nickname,
                //     token: res.data.token,
                //     email: res.data.email,
                //   });
                // }
                sessionStorage.setItem("token", params.accessToken);
                sessionStorage.setItem("userid", res.data.id);
                sessionStorage.setItem("role", res.data.role);
                dispatch(actionCreators.addInfo(
                    {
                      token: params.accessToken,
                      id: res.data.id,
                      nickname: res.data.nickname,
                      wallet: res.data.wallet
                    }
                  ));
                joinSuccess();
                console.log("회원가입")
                navigate("/");
            })
            .catch(error => {
                const message = error.message;
                console.log("error", error);
                console.log("message", message);
                alert("로그인 실패");
                DispatchdeleteInfo();
                joinFail();
            });
        }
    }

    return(
        <>
            <NewNav />
            <div style={{height: '100vh'}}>
                <div className={Styles.kakojoin}> Join us_by kakao</div>
                <div className={Styles.plus}> 추가 정보 입력 </div>
                {/* <div className={Styles.squ}></div> */}
                <div className={Styles.squ1}></div>
                <div className={Styles.squ2}></div>
                <input type={"text"} placeholder="Nickname" name="nickname" onChange={getNickname} className={Styles.nickname} />
                <button onClick={getChecknick} disabled={checknick === true} className={Styles.nicknamebtn}>중복확인</button>
                <input type={"text"} placeholder="PhoneNumber" onChange={getPhonenum} name="phone" className={Styles.phone}/>
                <button onClick={handleClickOpen} disabled={phonelock === true} className={Styles.phonebtn}>본인인증</button>
                <Dialog open={open} onClose={handleClose}>
                    <DialogTitle className={Styles.phonetitle}>휴대폰 인증</DialogTitle>
                    <DialogContent>
                        <DialogContentText className={Styles.phonetitle}>
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
                            className={Styles.phonetitle}
                            onChange={getChecknum}
                        />
                    </DialogContent>
                    <DialogActions>
                        <button onClick={handleClose} className={Styles.phonemodalclose}>Cancel</button>
                        <button onClick={getPhone} className={Styles.phonemodalSubscribe}>Subscribe</button>
                    </DialogActions>
                </Dialog>
                <input type={"checkbox"} className={Styles.privatecheck} onClick={getPcheck} />
                <div className={Styles.privateset}>
                    <span>
                        <Private2 />
                        <p className={Styles.private}> 동의</p>
                    </span>
                </div>

                <button className={Styles.join} onClick={joinus}>회원가입 진행하기</button>
                <div className={Styles.squ3}></div>
                <div className={Styles.squ4}></div>
            </div>
        </>
    );
}

export default Kakaojoin;