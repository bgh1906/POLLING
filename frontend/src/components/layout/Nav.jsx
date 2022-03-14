import { Link } from "react-router-dom";
import Styles from "./Nav.module.css";
import votebox from "./vote.png";

function Nav() {

    return (
        <div className={Styles.NavBox}>
        {/* 서비스 이름 */}
            <div>
                <div className={Styles.leftbox}>
                    <div className={Styles.leftboxbottom}></div>
                </div>
                <div className={Styles.header}> 
                    POLLING
                    <div>
                        <img src={votebox} height={20} alt="poll box"></img>
                    </div>
                </div>
                {/* <div className={Styles.boxtop}></div> */}
                {/* <span className={Styles.pollbox}>
                    <img src={votebox} height={30} alt="poll box"></img>
                </span> */}
                <div className={Styles.rightbox}>
                    <div className={Styles.rightboxtop}></div>
                </div>
            </div>

        {/* 메뉴 */}
        <div className={Styles.nav}>
        </div>
            <Link to="/hall"><span className={Styles.hall}> 명예의 전당 </span></Link>
            <Link to="/polllist"><span className={Styles.poll}> 투표 참여 </span></Link>
            <Link to="/history"><span className={Styles.history}> History </span></Link>
            <Link to="/notice"><span className={Styles.notice}> 공지사항 </span></Link>
            <Link to="/login"><span className={Styles.login}> 로그인 </span></Link>

        </div>
    );
}

export default Nav;