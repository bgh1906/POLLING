import { Link,useNavigate } from "react-router-dom";
import Nav from "../components/layout/Nav.jsx";
import Footer from "../components/layout/Footer.jsx";
import Styles from "./User.module.css";
import { Collapse } from "bootstrap";
import { useState } from "react";
import { Button } from "bootstrap";
import axios from "axios";
import { Table } from "react-bootstrap";

function User() {

    const [clickCom, setClickCom] = useState('#FEFFF8');
    // const [clickUser, setClickUser] = useState(Styles.other);
    
    function changeColor() {
        setClickCom('#caceb7');
    }

    //기업 회원가입 띄우기
    const [open, setOpen] = useState(true);
    const getOpen = () => {
        setOpen(!open);
        if(openO === false){
            setOpenO(true);
        }
        //펼쳐지면 버튼색 진해지게 -> 와이 안됨?
        if(open === false){
            // changeColor();
            clickCom === '#FEFFF8' ? setClickCom('#caceb7') : setClickCom('#FEFFF8');
            // setClickCom('#caceb7');
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

    }

    //id 받아오기
    const [id, setId] = useState("");
    const getId = (e) => {
        setId(e.target.value);
        console.log(id);
    };

    //비밀번호 받아오기
    const [password, setPassword] = useState("");
    const getPassword = (e) => {
        setPassword(e.target.value);
        console.log(password);
    };

    //페이지 이동
    const navigate = useNavigate();

    // 회원가입하기
    const onLogin = (e) => {
        if(id === "" || password ===""){
            e.preventDefault();
            alert("이메일/비밀번호를 입력해주세요");
        } else if(id !== "" && password !==""){
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
            alert("회원가입");
        }
    };

    return (
        <div>
            <Nav/>
            <div className={Styles.user}>User Mgt</div>
                <div className={Styles.addcompany} onClick={getOpen}>
                    <summary> 기업 회원 가입 </summary>
                </div>
                <form hidden={open}>
                    <div className={Styles.userbg}> </div>
                    <input type="text" placeholder="ID" className={Styles.id} onChange={getId} name="id"/>
                    <input type="password" placeholder=" Password" className={Styles.password} onChange={getPassword} name="password"/>
                    <button className={Styles.signinbtn}>Create</button>
                </form>

                <div className={Styles.other} onClick={getOpenO}>
                 <summary>회원 관리</summary>
                </div>
                
                <div hidden={openO}>
                    <input className={Styles.userEmail} type="text" placeholder="회원 이메일" />
                    {/* <button className={Styles.search}><img src="https://img.icons8.com/pastel-glyph/64/000000/search--v2.png"/></button> */}
                    <button className={Styles.searchbtn}></button>

                    {/* 회원리스트 주루륵 */}
                    <div>
                        <Table className={Styles.table}>
                            <thead className={Styles.thead}>
                                <tr>
                                    <th>#</th>
                                    <th>Nickname</th>
                                    <th>E-mail</th>
                                    <th>휴대폰 인증여부</th>
                                </tr>
                            </thead>
                            <tbody className={Styles.tbody}>
                                <tr>
                                    <td>{""}1</td>
                                    <td>신짱아 짱</td>
                                    <td>ssafy@ssafy</td>
                                    <td>인증</td>
                                </tr>
                            </tbody>
                        </Table>
                    </div>
                </div>
                

            {/* <div className={Styles.other}>
               <span id="more" style={{CURSOR: "hand"}} onclick={(story.style.display=='none')? (story.style.display='none' && more.innerText='>접기') : (story.style.display='none';more.innerText='>펼치기')}>펼치기</span>
                <div id="story" style={{display: ""}}><h>이곳에 필요한 내용을 입력하면 된다.</h></div>
            </div> */}


            {/* <Footer /> */}
        </div>
    );
}

export default User;