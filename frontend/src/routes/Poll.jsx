import Footer from "../components/layout/Footer";
import Nav from "../components/layout/Nav";
import Newnav from "../components/layout/NewNav";
import styles from "./Poll.module.css";

import { Link, useNavigate } from "react-router-dom";

function Poll() {



  return (
    <div>
      <Newnav />
      <div className={styles.poll_title}>POLL</div>

      <Link to='/poll/10/8/1'>1123</Link>
      <Footer />
    </div>
  );
}

export default Poll;
