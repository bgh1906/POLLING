import { Link } from "react-router-dom";
import Styles from "./Nav.module.css";
import votebox from "./vote.png";

function Nav() {

    return (
        <>
            {/* 서비스 이름 */}
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

            {/* 메뉴 */}
                <div className={Styles.nav}></div>
                <Link to="/hall" className={Styles.hall}>명예의 전당</Link>
                <Link to="/polllist" className={Styles.poll}> 투표 참여 </Link>
                <Link to="/history" className={Styles.history}> History</Link>
                <Link to="/notice" className={Styles.notice}> 공지사항</Link>
                <Link to="/login" className={Styles.login}> 로그인</Link>
                <Link to="/admin" className={Styles.admin}> 관리자페이지</Link>
        </>
    );
}

export default Nav;