import { Link, useNavigate } from "react-router-dom";
import Footer from "../components/layout/Footer.jsx";
import Styles from "./Companylogin.module.css";
import kakao from "../assets/kakao.png";
import { useState } from "react";
import axios from "axios";
import NewNav from "../components/layout/NewNav.jsx";

function Companylogin() {

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

    // 로그인하기
    const onLogin = (e) => {
        if(id === "" || password === ""){
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
            alert("로그인");
        }
    };

    //기업 회원가입 띄우기
    const [open, setOpen] = useState(true);
    const getOpen = () => {
        setOpen(!open);
    }

    return (
        <div>
            <NewNav/>
            <div className={Styles.signin}>Sign in _ <h className={Styles.company} >기업 로그인</h></div>
            <div className={Styles.loginbg}>
                {/* <div className={Styles.addcompany}>
                        <summary> 기업 로그인 </summary>
                </div> */}
                <form>
                    <div className={Styles.userbg}> </div>
                    <input type="text" placeholder=" Business ID" className={Styles.id} onChange={getId} name="id"/>
                    <input type="password" placeholder=" Password" className={Styles.password} onChange={getPassword} name="password"/>
                    <button className={Styles.signinbtn} onClick={onLogin}>Sign in</button>
                </form>
                <Link to="/login" className={Styles.login}>일반회원이신가요?</Link>
            </div>



            {/* <Footer /> */}
        </div>
    );
}

export default Companylogin;