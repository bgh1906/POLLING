import { Link,useNavigate } from "react-router-dom";
import NewNav from "../components/layout/NewNav.jsx";
import Footer from "../components/layout/Footer.jsx";
import Styles from "./User.module.css";
import { Collapse } from "bootstrap";
import { useState } from "react";
import { Button } from "bootstrap";
import axios from "axios";
// import { Table } from "react-bootstrap";
import UserSearch from "../components/admin/Usesearch.jsx";

import * as React from 'react';
import UserSearch2 from "../components/admin/Usesearch2.jsx";
import Userqnalist from "../components/admin/Userqnalist.jsx";


function User() {

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


    //페이지 이동
    const navigate = useNavigate();

    // 회원가입하기
    const onLogin = (e) => {
        if(nickname ===" " || email === " " || password === " " || phone === " "){
            e.preventDefault();
            alert("닉네임/이메일/비밀번호를 입력하세요.")
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
                alert("회원가입 성공!")
                // navigate("/login");
            })
            .catch(error => {
                const message = error.message;
                console.log("message", message);
                alert("회원가입 실패");
                // setSubmitError(message);
                // setTimeout(() => {
                //   setSubmitError(null);
                // }, 3000);
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
                            <input type={"text"} placeholder=" Business_name " className={Styles.id} onChange={getId} name="nickname"/>
                            <input type={"email"} placeholder=" email" className={Styles.email} onChange={getEmail} name="email"/>
                            <input type={"password"} placeholder=" Password" className={Styles.password} onChange={getPassword} name="password"/>
                            <input type={"text"} placeholder=" PhoneNumber(01012345678) " className={Styles.phone} onChange={getPhone} name="phone"/>
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