import { Link,useNavigate } from "react-router-dom";
import NewNav from "../components/layout/NewNav.jsx";
import Styles from "./User.module.css";
import { useState } from "react";
import axios from "axios";

import * as React from 'react';
import UserSearch2 from "../components/admin/Usesearch2.jsx";
import Userqnalist from "../components/admin/Userqnalist.jsx";
import Swal from "sweetalert2";
import { web3 } from "../contracts/CallContract";
import Tooltip, { tooltipClasses } from "@mui/material/Tooltip";
import { Typography } from "@mui/material";
import { styled } from "@mui/material/styles";

function User() {

    const token = sessionStorage.getItem("token")


    const [clickCom, setClickCom] = useState('#FEFFF8');
    
    function changeColor() {
        setClickCom('#caceb7');
    }

    //기업 회원가입 띄우기_기본값은 true로 hidden
    const [open, setOpen] = useState(true);
    const getOpen = () => {
        //기업관리 값 변경시
        setOpen(!open);
        //회원관리가 켜져있다면
        if(openO === false){
            //hidden처리 하기.
            setOpenO(true);
        }
        if(openL === false){
            //hidden처리 하기.
            setOpenO(true);
        }
    }

    //회원 관리 띄우기
    const [openO, setOpenO] = useState(true);
    const getOpenO = () => {
        setOpenO(!openO);
        if(open === false){
            setOpen(true);
        }
        if(openL === false){
            setOpenL(true);
        }
    }

    //회원 문의 리스트
    const [openL, setOpenL] = useState(true);
    const getOpenL = () => {
        setOpenL(!openL);
        if(open === false){
            setOpen(true);
        }
        if(openO === false){
            setOpenO(true);
        }
    }

    // 담당자 이메일 받아오기
    const [email, setEmail] = useState("");
    const getEmail = (e) => {
        setEmail(e.target.value);
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

    //nickname -> 회사명 받아오기
    const [nickname, setId] = useState("");
    const getId = (e) => {
        setId(e.target.value);
    };

    //닉네임 중복 체크
    const [checknick, setChecknick] = useState(false);

    const getChecknick = (e) => {
        if (nickname === "") {
            e.preventDefault();
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
            .catch((error) => {
                if (error.code === 409) {
                    samenick();
                }
                setId("");
                });
        }
    };

    //비밀번호 받아오기
    const [password, setPassword] = useState("");
    const getPassword = (e) => {
        setPassword(e.target.value);
    };

    //담당자 번호
    const [phone, setPhone] = useState("");
    const getPhone = (e) => {
        setPhone(e.target.value);
    };

    //계좌 비밀번호
    const [walletpw, setWalletpw] = useState("");
    const [userAccount, setUserAccount] = useState("");
    const getWalletpw = (e) => {
      setWalletpw(e.target.value);
    };
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

    //alert 창_회원가입 
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

    //빈칸확인
    const inputnull = () => {
        Swal.fire({
          text:"닉네임/이메일/비밀번호/휴대폰번호/계좌 비밀번호를 입력하세요.",
          icon: 'error',
          confirmButtonColor: '#73E0C1',
          confirmButtonText: '확인'
        })
    }

    //페이지 이동
    const navigate = useNavigate();

    // 회원가입하기
    const onLogin = async (e) => {
        if(nickname ===" " || email === " " || password === " " || phone === " " || walletpw === " "){
            e.preventDefault();
            inputnull();
        } 
        else if(nickname !== " " && email !== " " && password !== " " && walletpw !== " " ){
            const wallet = await createWallet();

            axios
            .post(
                "/api/members",
                // "http://j6a304.p.ssafy.io/api/members",
                {
                    email: email,
                    nickname: nickname,
                    password: password,
                    phoneNumber: phone,
                    wallet: wallet,
                    role: "ROLE_COMPANY"
                },
            )
            .then((res) => {
                setId("");
                setEmail("");
                setPassword("");
                setPhone("");
                setChecknick(false);
                joinSuccess();
            })
            .catch(error => {
                const message = error.message;
                joinFail();
              });
        }
    };



    return (
        <div style={{height:'100vh'}}>
            <NewNav />
            <div className={Styles.user}>User Mgt</div>
                <div className={Styles.nav}>
                    <div className={Styles.addcompany} onClick={getOpen}>
                        <summary> 기업 회원 가입 </summary>
                    </div>
                    <div className={Styles.other} onClick={getOpenO}>
                    <summary>회원 관리</summary>
                    </div>
                    <div className={Styles.qna} onClick={getOpenL}>
                    <summary>1:1 문의</summary>
                    </div>
                </div>

                <div hidden={open}>
                    <div className={Styles.login}>
                        <div> 
                            <input type={"text"} placeholder=" Business_name " className={Styles.id} onChange={getId} name="nickname" maxLength="12"/>
                            <button className={Styles.nicknameCheck} onClick={getChecknick} disabled={checknick === true}>
                                중복확인
                            </button>
                            <input type={"email"} placeholder=" email" className={Styles.email} onChange={getEmail} name="email"/>
                            <input type={"password"} placeholder=" Password" className={Styles.password} onChange={getPassword} name="password" maxLength="13"/>
                            <input type={"text"} placeholder=" PhoneNumber(01012345678) " className={Styles.phone} onChange={getPhone} name="phone"/>
                            <input type={"password"} placeholder=" Wallet Password " className={Styles.walletpassword} onChange={getWalletpw} name="walletpassword"/>
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
                            <button className={Styles.signinbtn} onClick={onLogin}>Create</button>
                        </div>
                    </div>
                </div>

                <div hidden={openO}>
                    {/* 회원리스트 주루륵 */}

                    <UserSearch2 />
                </div>
                
                <div hidden={openL}>
                    {/* 1:1문의 내용 주르륵 */}
                    <Userqnalist />
                </div>
        </div>
    );
}

export default User;