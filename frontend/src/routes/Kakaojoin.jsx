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

import Tooltip, { tooltipClasses } from "@mui/material/Tooltip";
import ClickAwayListener from "@mui/material/ClickAwayListener";
import { styled } from "@mui/material/styles";
import { Typography } from "@mui/material";
import { web3 } from "../contracts/CallContract";

function Kakaojoin({ DispatchdeleteInfo }) {

    React.useEffect(() => {
        setNickname("");
        setPhone("");
        setWalletpw("");
      }, []);

    const params = useParams();

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
    };

    //닉네임 사용 가능
    const usenick = () => {
        Swal.fire({
          text:"사용가능한 닉네임입니다.",
          icon: 'success',
          confirmButtonColor: '#73E0C1',
          confirmButtonText: '확인'
        })
    }

    //닉네임 중복
    const samenick = () => {
        Swal.fire({
          text:"동일 닉네임이 존재합니다.",
          icon: 'error',
          confirmButtonColor: '#73E0C1',
          confirmButtonText: '확인'
        })
    }

    //닉네임 빈값
    const nicknull = () => {
        Swal.fire({
          text:"Nickname을 입력해주세요.",
          icon: 'error',
          confirmButtonColor: '#73E0C1',
          confirmButtonText: '확인'
        })
    }

    //닉네임 중복 체크
    const [checknick, setChecknick] = useState(false);
    
    const getChecknick = (e) => {
        if(nickname === "") {
            nicknull();
        } else {
            axios
            .get(
                `https://j6a304.p.ssafy.io/api/members/nickname/${nickname}`,
                {
                    n: nickname,
                }           
            )
            .then((res) => {
                usenick();
                setChecknick(true);
            })
            .catch(error => {
                samenick();
                setNickname("");
            })
        }
    };

    //휴대전화 번호 입력
    const [phone, setPhone] = useState("");
    const getPhonenum = (e) =>{
        setPhone(e.target.value);
    }

    //폰번호 입력 
    const phonenull = () => {
        Swal.fire({
        text:"휴대폰 번호를 입력해주세요",
        icon: 'error',
        confirmButtonColor: '#73E0C1',
        confirmButtonText: '확인'
        })
    }
    
    //인증번호 전송
    const sendnum = () => {
        Swal.fire({
            text:"인증번호가 전송되었습니다!",
            icon: 'success',
            confirmButtonColor: '#73E0C1',
            confirmButtonText: '확인'
        })
    }
    
    //인증번호 전송 실패
    const sendfail = () => {
        Swal.fire({
            text:"인증번호 전송 실패!",
            icon: 'error',
            confirmButtonColor: '#73E0C1',
            confirmButtonText: '확인'
        })
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
            phonenull();
        }
        else if(phone !== "") {
            axios
            .post(
                "https://j6a304.p.ssafy.io/api/notify/sms",
                {
                    content : "",
                    to : phone,
                },
            )
            .then((res) => {
                console.log(res.data.code);
                sendnum();
                setOpen(true);
                setPhonelock(true);
                setRealNum(res.data.code);
            })
            .catch(error => {
                const message = error.message;
                // console.log("message", message);
                sendfail();
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
    }

    //인증번호 미입력
    const numnull = () => {
        Swal.fire({
        text:"인증번호를 입력해주세요",
        icon: 'error',
        confirmButtonColor: '#73E0C1',
        confirmButtonText: '확인'
        })
    }

    //인증번호 전송
    const numsucc = () => {
        Swal.fire({
          text:"본인 확인 완료",
          icon: 'success',
          confirmButtonColor: '#73E0C1',
          confirmButtonText: '확인'
        })
    }

    //인증번호 전송 실패
    const numfail = () => {
        Swal.fire({
          text:"인증번호가 틀렸습니다.",
          icon: 'error',
          confirmButtonColor: '#73E0C1',
          confirmButtonText: '확인'
        })
    }

    //인증번호 맞는지 체크
    const [phonecheck, setPhonecheck] = useState(false);
    const getPhone = (e) =>{
        //휴대폰인증 진행
        if(checknum === " "){
            e.preventDefault();
            numnull();
        }
        else if(checknum !== "") {
            if(checknum !== realNum ){
                numfail();
            }
            else if(checknum === realNum ){
                numsucc();
                //정상 처리되면 true로 바꾸기 & 모달 종료
                setPhonecheck(true);
                setOpen(false);
            }
        }
    }

    // 계좌 비밀번호 입력 -> 회원가입 -> 자동 계정 생성, 화면에 그 사람 (join에 인풋 만들기)
    const [walletpw, setWalletpw] = useState("");
    const [userAccount, setUserAccount] = useState("");
    const getWalletpw = (e) => {
        setWalletpw(e.target.value);
    }
    const createWallet = async () => {
        let userAccount = await web3.eth.personal.newAccount(walletpw);
        return userAccount;
        // setState는 비동기처리이기 때문에 바로 console에 변한 값이 출력되지 않음
    };
    //입력만 받아서 onchange에만 -> 유저가 관리, 서비스측에서 저장안함. 
    //스마트컨트랙트에 전송해서, 블록체인 계정 만들고, 나중에 유저가 투표할때 기본적으로 생성된 계정이 잠겨있는데,
    //그때 일시적으로 풀때 필요함. 그떄 동일 값 넣어야 계정이 일시적으로 열리면서 투표 진행, 진행 후 다시 잠김.
    //주의사항 명시 계좌 비밀번호는 사이트에서 관리하지않습니다 잊어버리는경우 알수없으니 보관시 주의 바랍니다.

     //계좌 비밀번호 툴팁용
     const [openW, setOpenW] = React.useState(false);

     const handleTooltipClose = () => {
         setOpenW(false);
     };
 
     //계좌 툴팁
     const handleTooltipOpen = () => {
         setOpenW(true);
     };
 
     const HtmlTooltip = styled(({ className, ...props }) => (
         <Tooltip {...props} classes={{ popper: className }} />
     ))(({ theme }) => ({
         [`& .${tooltipClasses.tooltip}`]: {
         backgroundColor: "#ffe6f1",
         color: "rgba(51, 51, 51, 0.87)",
         maxWidth: 300,
         fontSize: theme.typography.pxToRem(14),
         border: "1px solid #dadde9",
         fontFamily: 'GangwonEdu_OTFBoldA',
         }
     }));

    //개인정보처리방침 동의 여부
    const [pcheck, setPcheck] = useState(false);

    const getPcheck = (e) => {
        setPcheck(!pcheck);
    }

    //빈칸확인
    const inputnull = () => {
        Swal.fire({
            text:"닉네임/휴대폰번호/계좌 비밀번호를 입력하세요.",
            icon: 'error',
            confirmButtonColor: '#73E0C1',
            confirmButtonText: '확인'
        })
    }
    
        //인증 여부
        const phonechek = () => {
            Swal.fire({
              text:"휴대폰 인증을 진행해주세요.",
              icon: 'error',
              confirmButtonColor: '#73E0C1',
              confirmButtonText: '확인'
            })
        }
    
        //중복 체크 여부
        const nickchek = () => {
            Swal.fire({
              text:"닉네임 중복체크를 진행해주세요.",
              icon: 'error',
              confirmButtonColor: '#73E0C1',
              confirmButtonText: '확인'
            })
        }
    
        //개인정보처리 여부
        const privacychek = () => {
            Swal.fire({
              text:"개인정보처리방침에 동의해주세요.",
              icon: 'error',
              confirmButtonColor: '#73E0C1',
              confirmButtonText: '확인'
            })
        }

    //페이지 이동
    const navigate = useNavigate();

    //회원가입
    const joinus = async(e) => {
        if(nickname ===" " || phone === " " || walletpw === "" ){
            e.preventDefault();
            inputnull();
        } 
        else if( phonecheck === false){
            e.preventDefault();
            phonechek();
        } 
        else if( checknick === false){
            e.preventDefault();
            nickchek();
        } 
        else if( pcheck===false ){
            e.preventDefault();
            privacychek();
        }
        else if( nickname !== " " && phone !== " " && walletpw !== "" && phonecheck !== false && checknick !== false && pcheck !== false ){
            const wallet = await createWallet();
            console.log("wallet",wallet);
            axios
            .post("https://j6a304.p.ssafy.io/api/auth/social", 
            {
                accessToken: params.accessToken,
                nickname: nickname,
                phoneNumber: phone,
                wallet: wallet,
            })
            .then((res) => {
                // console.log("res",res);
                sessionStorage.setItem("token", params.accessToken);
                sessionStorage.setItem("userid", res.data.id);
                sessionStorage.setItem("role", res.data.role);
                // sessionStorage.setItem("wallet", res.data.wallet);
                sessionStorage.setItem("wallet", wallet);
                dispatch(actionCreators.addInfo(
                    {
                      id: res.data.id,
                      nickname: res.data.nickname,
                      token: params.accessToken,
                    //   wallet: res.data.wallet
                      wallet: wallet
                    }
                  ));
                joinSuccess();
                navigate("/");
            })
            .catch(error => {
                const message = error.message;
                console.log("message", message);
                console.log("error", error);
                // DispatchdeleteInfo();
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
                <input 
                    type={"password"} 
                    placeholder=" Wallet Password" 
                    className={Styles.walletpassword} 
                    onChange={getWalletpw} 
                    name="walletpassword" 
                    maxLength="13"
                />
                <HtmlTooltip
                    title={
                        <React.Fragment>
                            <Typography color="#f12b5c" fontWeight="800" fontFamily="GangwonEdu_OTFBoldA">주의</Typography>
                            <b>{"계좌 비밀번호는 사이트에서 관리하지 않습니다."}</b> <br/> 
                            {"잊어버리는 경우, 알 수 없으니 보관시 주의 바랍니다."}
                        </React.Fragment>
                    }
                    placement="top"
                >
                    <img className={Styles.walletimg} onClick={handleTooltipOpen} src="https://img.icons8.com/stickers/30/000000/high-importance.png"/>
                </HtmlTooltip>
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