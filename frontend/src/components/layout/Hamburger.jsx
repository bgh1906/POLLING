import { Link } from "react-router-dom";
import Styles from "./Hamburger.module.css";

function Hamburger() {


    return(
        <>
            <label>
                <input type='checkbox' />
                    <span class='menu' className={Styles.menu}>
                        <span class='hamburger' className={Styles.hamburger}></span>
                    </span>
                    <ul>
                    {/* <div> */}
                        <li></li>
                        <li>
                            {/* <a href='/'>Home</a> */}
                            <Link to='/' className={Styles.home}>Home</Link>
                        </li>
                        <li>
                            {/* <a href='/'>Home</a> */}
                            <Link to='/login' className={Styles.home}>login</Link>
                        </li>
                        <li>
                            {/* <a href='/polllist'>Poll</a> */}
                            <Link to='/polllist' className={Styles.poll}>Poll</Link>
                        </li>
                        <li>
                            {/* <a href='/hall'>Hall</a> */}
                            <Link to='/hall' className={Styles.hall}>Hall</Link>
                        </li>
                        <li>
                            {/* <a href='/history'>History</a> */}
                            <Link to='/history' className={Styles.history}>History</Link>
                        </li>
                    </ul>
                    {/* </div> */}
            </label>
        </>
    )
}

export default Hamburger;