import Footer from "../components/layout/Footer";
import Nav from "../components/layout/Nav";
import Newnav from "../components/layout/NewNav";
import styles from "./Poll.module.css";


function Poll() {



  return (
    <div>
      <Newnav />
      <div className={styles.poll_title}>POLL</div>

      <Footer />
    </div>
  );
}

export default Poll;
