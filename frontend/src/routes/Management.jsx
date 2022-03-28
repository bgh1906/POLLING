import { Link } from "react-router-dom";
import Nav from "../components/layout/Nav.jsx";
import Footer from "../components/layout/Footer.jsx";
import Styles from "./Management.module.css";

function Management() {

    return (
        <div>
            <Nav/>
            <div className={Styles.management}>Management</div>
            <div className={Styles.managementbg}>
                <Link to='/user' className={Styles.user}> <div> 회원관리 </div> </Link>
                <Link to='/admin' className={Styles.poll}> <div> 투표관리 </div> </Link>
            </div>

            {/* <Footer /> */}
        </div>
    );
}

export default Management;