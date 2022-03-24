import Footer from "../components/layout/Footer";
import styles from "./History.module.css";
import chunhyang from "../assets/chunhyang.PNG";
import fox from "../assets/fox.PNG";
import ocean from "../assets/ocean.PNG";
import eyes from "../assets/eyes.PNG";
import logoContest from "../assets/logoContest.PNG";
import monarisa from "../assets/monarisa.PNG";
import hair from "../assets/hair.PNG";
import gogh from "../assets/gogh.PNG";
import { useState } from "react";
import Newnav from "../components/layout/NewNav";
import { Button } from "@mui/material";
import Aos from "aos";
import "aos/dist/aos.css";

function History() {
  const [modalShow, setModalShow] = useState(false);
  const endItemData = [
    {
      img: chunhyang,
      title: "chunhyang",
    },
    {
      img: fox,
      title: "fox",
    },
    {
      img: ocean,
      title: "ocean",
    },
    {
      img: eyes,
      title: "eyes",
    },
    {
      img: logoContest,
      title: "logoContest",
    },
    {
      img: gogh,
      title: "gogh",
    },
    {
      img: monarisa,
      title: "monarisa",
    },
    {
      img: hair,
      title: "hair",
    },
  ];
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
      <div className={styles.history_container}>
        <div className={styles.history_itemlist}>
          {endItemData.map((item) => (
            // <div className={styles.history_item}>
            <div className={styles.history_item} data-aos="fade-up">
              <img src={item.img} alt={item.title} />
              <div className={styles.ended_info}>
                <div style={{ fontSize: "3vw", fontWeight: 700 }}>
                  {item.title}
                </div>
                <div style={{ fontSize: "1.2vw" }}>2022.03.03 ~ 2022.05.10</div>
              </div>
              <div className={styles.ended_button}>
                <Button
                  variant="contained"
                  size="large"
                  style={{ backgroundColor: "#FB71AB" }}
                  onClick={() => setModalShow(true)}
                >
                  투표결과
                </Button>
              </div>
              <figcaption>
                <span>2022</span>
                {item.title}
              </figcaption>
            </div>
          ))}
        </div>
      </div>
      <Footer />
    </>
  );
}

export default History;
