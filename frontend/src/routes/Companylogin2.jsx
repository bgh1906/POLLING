import { Link, useNavigate } from "react-router-dom";
import Footer from "../components/layout/Footer.jsx";
import Styles2 from "./Companylogin2.module.css";
import { useState } from "react";
import axios from "axios";
import NewNav from "../components/layout/NewNav.jsx";

function Companylogin2() {

    //id 받아오기
    
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

    //페이지 이동
    const navigate = useNavigate();

    // 로그인하기
    const onLogin = (e) => {
        if(email === "" || password === ""){
            e.preventDefault();
            alert("이메일/비밀번호를 입력해주세요");
        } else if(email !== "" && password !==""){
            axios
              .post(
                // "http://j6a304.p.ssafy.io:8080/api/auth",
                "http://j6a304.p.ssafy.io/api/auth",
                  {
                    email: email,
                    password: password,
                  },
              )
            .then((res) => {
                console.log("res", res);
                console.log("로그인 성공");
                alert("로그인 성공");
                navigate("/");
            })
            .catch(error => {
                const message = error.message;
                console.log("message", message);
                alert("로그인 실패");
              });
        }
    };

    return (
        <div>
            <NewNav/>
            <div className={Styles2.container}>
                <div className={Styles2.top}></div>
                <div className={Styles2.bottom}></div>
                <div className={Styles2.center}>
                    <div className={Styles2.signin}>Sign in_Business</div>
                    {/* <form> */}
                        {/* <div className={Styles2.userbg}> </div> */}
                        <input type="text" placeholder=" Business E-mail" className={Styles2.id} onChange={getEmail} name="email"/>
                        <input type={"password"} placeholder=" Password" className={Styles2.password} onChange={getPassword} name="password"/>
                        <button className={Styles2.signinbtn} onClick={onLogin}>Sign in</button>
                    {/* </form> */}
                <Link to="/login" className={Styles2.login}>일반회원이신가요?</Link>
                </div>
            </div>
            {/* <Footer /> */}
        </div>
    );
}

export default Companylogin2;