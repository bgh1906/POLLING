import { useState } from "react";
import NewNav from "../components/layout/NewNav";
import Styles from "./Qna.module.css"




function Qna() {

    return (
        <>
        
        {/* <NewNav /> */}
        <div>
            <div className={Styles.title}>
                TITLE
            </div>
            <input type={"text"} className={Styles.titleC} placeholder="제목을 입력하세요"></input>
        </div>
        <div>
            <div className={Styles.email}>
                E-MAIL
            </div>
            <input type={"email"} className={Styles.emailC}  placeholder="답변 받을 메일을 적어주세요"></input>
            <div>
                TYPE
            </div>
        </div>
        <div>
            <div className={Styles.content}>
                CONTENT
            </div>
            <textarea className={Styles.contentC}  placeholder="내용을 입력하세요"></textarea>
        </div>
        {/* <button className={Styles.list} onClick={getList}>목록</button> */}
        <button className={Styles.save}>submit</button>
        </>
    );
}

export default Qna;