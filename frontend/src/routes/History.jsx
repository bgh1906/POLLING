import Footer from "../components/layout/Footer";
import styles from "./History.module.css";
import { useState } from "react";
import Newnav from "../components/layout/NewNav";
import Aos from "aos";
import "aos/dist/aos.css";
import { useEffect } from "react";
import axios from "axios";
import Historytxid from "./Historytxid";

function History() {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const [itemData, setItemData] = useState([]);

  useEffect(() => {
    axios
      .get("https://j6a304.p.ssafy.io/api/polls/done/0/50")
      .then((res) => {
        // console.log(res);
        setItemData(res.data);
      })
      .catch((error) => {
        // console.log(error.response);
      });
  }, []);

  Aos.init({
    // Global settings:
    disable: false, // accepts following values: 'phone', 'tablet', 'mobile', boolean, expression or function
    startEvent: "DOMContentLoaded", // name of the event dispatched on the document, that AOS should initialize on
    initClassName: "aos-init", // class applied after initialization
    animatedClassName: "aos-animate", // class applied on animation
    useClassNames: false, // if true, will add content of `data-aos` as classes on scroll
    disableMutationObserver: false, // disables automatic mutations' detections (advanced)
    debounceDelay: 50, // the delay on debounce used while resizing window (advanced)
    throttleDelay: 99, // the delay on throttle used while scrolling the page (advanced)

    // Settings that can be overridden on per-element basis, by `data-aos-*` attributes:
    offset: 120, // offset (in px) from the original trigger point
    delay: 600, // values from 0 to 3000, with step 50ms
    duration: 500, // values from 0 to 3000, with step 50ms
    easing: "ease", // default easing for AOS animations
    once: false, // whether animation should happen only once - while scrolling down
    mirror: false, // whether elements should animate out while scrolling past them
    anchorPlacement: "top-bottom", // defines which position of the element regarding to window should trigger the animation
  });
  return (
    <>
      <Newnav />
      <div className={styles.history_title}>History</div>
      <div className={styles.history_desc}>
        종료된 투표를 확인하고 전체 투표 내역을 조회할 수 있습니다.
      </div>
      <div className={styles.history_container}>
        <div className={styles.history_itemlist}>
          {itemData.map((item, index) => (
            <div className={styles.history_item} key={index} data-aos="fade-up">
              <img src={item.thumbnail} alt={item.title} />
              <div className={styles.ended_info}>
                <div id={styles.name_info}>{item.title}</div>
                <div id={styles.date_info}>
                  {item.startDate} <br /> {item.endDate}
                </div>
              </div>
              <div className={styles.ended_button}>
                <Historytxid pollId={item.pollId} />
              </div>
            </div>
          ))}
        </div>
      </div>
      <Footer />
    </>
  );
}

export default History;
