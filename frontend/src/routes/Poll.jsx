import Footer from "../components/layout/Footer";
import Nav from "../components/layout/Nav";
import styles from "./Poll.module.css";

function Poll() {
  return (
    <div>
      <Nav />
      <div className={styles.poll_title}>POLL</div>
      <Footer />
    </div>
  );
}

export default Poll;
