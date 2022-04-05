import { Link,useNavigate } from "react-router-dom";
import NewNav from "../components/layout/NewNav.jsx";
import Footer from "../components/layout/Footer.jsx";
import Styles from "./User.module.css";
import { Collapse } from "bootstrap";
import { useState } from "react";
import { Button } from "bootstrap";
import axios from "axios";
// import { Table } from "react-bootstrap";

import * as React from 'react';
import UserSearch2 from "../components/admin/Usesearch2.jsx";
import Userqnalist from "../components/admin/Userqnalist.jsx";
import Swal from "sweetalert2";

function User() {

    const token = sessionStorage.getItem("token")


    const [clickCom, setClickCom] = useState('#FEFFF8');
    // const [clickUser, setClickUser] = useState(Styles.other);
    
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
            // setOpenL(true);
        }
        if(openL === false){
            //hidden처리 하기.
            setOpenO(true);
            // setOpenL(true);
        }
    }

    //회원 관리 띄우기
    const [openO, setOpenO] = useState(true);
    const getOpenO = () => {
        setOpenO(!openO);
        // if(open === false){
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
        // if(open === false){
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
        console.log(email);
    };

    //nickname -> 회사명 받아오기
    const [nickname, setId] = useState("");
    const getId = (e) => {
        setId(e.target.value);
        console.log(nickname);
    };

    //비밀번호 받아오기
    const [password, setPassword] = useState("");
    const getPassword = (e) => {
        setPassword(e.target.value);
        console.log(password);
    };

    //담당자 번호
    const [phone, setPhone] = useState("");
    const getPhone = (e) => {
        setPhone(e.target.value);
        console.log(phone);
    };

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
          text:"닉네임/이메일/비밀번호/휴대폰번호를 입력하세요.",
          icon: 'error',
          confirmButtonColor: '#73E0C1',
          confirmButtonText: '확인'
        })
    }

    //페이지 이동
    const navigate = useNavigate();

    // 회원가입하기
    const onLogin = (e) => {
        if(nickname ===" " || email === " " || password === " " || phone === " "){
            e.preventDefault();
            // alert("닉네임/이메일/비밀번호/휴대폰번호를 입력하세요.")
            inputnull();
        } 
        else if(nickname !== " " && email !== " " && password !== " " ){
            axios
            .post(
                // "http://j6a304.p.ssafy.io:8080/api/members",
                "http://j6a304.p.ssafy.io/api/members",
                {
                    email: email,
                    nickname: nickname,
                    password: password,
                    phoneNumber: phone,
                    role: "ROLE_COMPANY"
                },
            )
            .then((res) => {
                console.log("res", res);
                // alert("회원가입 성공!")
                joinSuccess();
                // navigate("/login");
            })
            .catch(error => {
                const message = error.message;
                console.log("message", message);
                // alert("회원가입 실패");
                joinFail();
                // setSubmitError(message);
                // setTimeout(() => {
                //   setSubmitError(null);
                // }, 3000);
              });
        }
    };

    //   //유저목록 받기
    // const [rows, setRows] = useState([]);


    // //회원리스트 뽑기
    // React.useEffect(() => {
    //     axios
    //     .get(
    //       "https://j6a304.p.ssafy.io/api/members",
    //       {
    //         headers: {
    //           "Authorization":token,
    //         },
    //       }
    //     )
    //     .then((res) => {
    //       console.log("data",res.data);
    //       setRows(res.data);
    //     })
    //     .catch(error => {
    //       console.log("res,userlist",error.response);
    //       console.log("error,userlist",error);
    //     })
    //   },[])

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
                            <input type={"email"} placeholder=" email" className={Styles.email} onChange={getEmail} name="email"/>
                            <input type={"password"} placeholder=" Password" className={Styles.password} onChange={getPassword} name="password" maxLength="13"/>
                            <input type={"text"} placeholder=" PhoneNumber(01012345678) " className={Styles.phone} onChange={getPhone} name="phone"/>
                            <button className={Styles.signinbtn} onClick={onLogin}>Create</button>
                        </div>
                    </div>
                </div>

                <div hidden={openO}>
                    {/* 회원리스트 주루륵 */}

                    {/* <UserSearch2 id={rows.id} nickname={rows.nickname} email={rows.email} /> */}
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