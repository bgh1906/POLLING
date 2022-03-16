import { Link } from "react-router-dom";
import Nav from "../components/layout/Nav.jsx";
import Footer from "../components/layout/Footer.jsx";
import Styles from "./User.module.css";
import { Collapse } from "bootstrap";
import { useState } from "react";
import { Button } from "bootstrap";

function User() {

    const [open, setOpen] = useState(false);
    const [openO, setOpenO] = useState(false);

    return (
        <div>
            <Nav/>
            <div className={Styles.user}>User Mgt</div>
            <div className={Styles.userbg}>
               <details className={Styles.addcompany}>
                   <summary> 기업 회원 가입 </summary>
                   <form>
                    <input type="text" placeholder="ID" className={Styles.id}/>
                    <input type="password" placeholder=" Password" className={Styles.password}/>
                    <button className={Styles.signinbtn}>Sign in</button>
                </form>
               </details>

               <details className={Styles.other}>
                <summary>회원 관리</summary>
               </details>
                

            {/* <div className={Styles.other}>
               <span id="more" style={{CURSOR: "hand"}} onclick={(story.style.display=='none')? (story.style.display='none' && more.innerText='>접기') : (story.style.display='none';more.innerText='>펼치기')}>펼치기</span>
                <div id="story" style={{display: ""}}><h>이곳에 필요한 내용을 입력하면 된다.</h></div>
            </div> */}


            {/* <Footer /> */}
            </div>
        </div>
    );
}

export default User;