import { Link } from "react-router-dom";
import Footer from "../components/layout/Footer.jsx";
import Styles from "./Management.module.css";
import NewNav from "../components/layout/NewNav.jsx";

function Management() {

    return (
        <div style={{height:'100vh'}}>
            <NewNav/>
            <div className={Styles.management}>Management</div>
            {/* <div className={Styles.managementbg}> */}
            <div>
                <Link to='/user' className={Styles.user}> User Mgt </Link>
                <Link to='/admin' className={Styles.poll}> Poll Mgt </Link>
            </div>

            {/* <Footer /> */}
        </div>
    );
}

export default Management;