import Footer from "../components/layout/Footer";
import styles from "./PollList.module.css";
import chunhyang from "../assets/chunhyang.PNG";
import ocean from "../assets/ocean.PNG";
import ev9 from "../assets/ev9.PNG";
import kClassic from "../assets/kClassic.PNG";
import logoContest from "../assets/logoContest.PNG";
import missKorea from "../assets/missKorea.PNG";
import eyes from "../assets/eyes.PNG";
import fox from "../assets/fox.PNG";
import gogh from "../assets/gogh.PNG";
import monarisa from "../assets/monarisa.PNG";
import hair from "../assets/hair.PNG";
import { useNavigate } from "react-router-dom";
import Newnav from "../components/layout/NewNav";
import Box from "@mui/material/Box";
import ImageList from "@mui/material/ImageList";
import ImageListItem from "@mui/material/ImageListItem";

export default function PollList() {
  const navigate = useNavigate();
  return (
    <div className={styles.polllist}>
      <Newnav />
      <div className={styles.list_title}>Poll List ?Center</div>
      <Box
        sx={{ width: 1600, height: 1080 }}
        // style={{ display: "flex", justifyContent: "center" }}
      >
        <ImageList variant="masonry" cols={4} gap={28}>
          {itemData.map((item) => (
            <ImageListItem key={item.img}>
              <img
                src={`${item.img}?w=248&fit=crop&auto=format`}
                srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
                alt={item.title}
                loading="lazy"
              />
            </ImageListItem>
          ))}
        </ImageList>
      </Box>
      <Footer />
    </div>
  );
}
// <div className={styles.list_container}>
//   <div className={styles.list_item}>
//     <img src={chunhyang} alt="img1" className={styles.image} />
//     <div className={styles.middle}>
//       <div>제 91회 춘향제</div>
//       <br />
//       <div>2022.03.03 ~ 2022.05.10</div>
//     </div>
//     <div className={styles.bottom}>
//       <div
//         className={styles.button}
//         onClick={() => {
//           navigate("/poll/:pollnum");
//         }}
//       >
//         투표하기
//       </div>
//     </div>
//   </div>
//   <div className={styles.list_item}>
//     <img src={ocean} alt="img2" className={styles.image} />
//     <div className={styles.middle}>
//       <div>제 91회 춘향제</div>
//       <br />
//       <div>2022.03.03 ~ 2022.05.10</div>
//     </div>
//     <div className={styles.bottom}>
//       <div
//         className={styles.button}
//         onClick={() => {
//           navigate("/poll/:pollnum");
//         }}
//       >
//         투표하기
//       </div>
//     </div>
//   </div>
//   <div className={styles.list_item}>
//     <img src={ev9} alt="img3" className={styles.image} />
//     <div className={styles.middle}>
//       <div>제 91회 춘향제</div>
//       <br />
//       <div>2022.03.03 ~ 2022.05.10</div>
//     </div>
//     <div className={styles.bottom}>
//       <div
//         className={styles.button}
//         onClick={() => {
//           navigate("/poll/:pollnum");
//         }}
//       >
//         투표하기
//       </div>
//     </div>
//   </div>
//   <div className={styles.list_item}>
//     <img src={kClassic} alt="img4" className={styles.image} />
//     <div className={styles.middle}>
//       <div>제 91회 춘향제</div>
//       <br />
//       <div>2022.03.03 ~ 2022.05.10</div>
//     </div>
//     <div className={styles.bottom}>
//       <div
//         className={styles.button}
//         onClick={() => {
//           navigate("/poll/:pollnum");
//         }}
//       >
//         투표하기
//       </div>
//     </div>
//   </div>
//   <div className={styles.list_item}>
//     <img src={logoContest} alt="img5" className={styles.image} />
//     <div className={styles.middle}>
//       <div>제 91회 춘향제</div>
//       <br />
//       <div>2022.03.03 ~ 2022.05.10</div>
//     </div>
//     <div className={styles.bottom}>
//       <div
//         className={styles.button}
//         onClick={() => {
//           navigate("/poll/:pollnum");
//         }}
//       >
//         투표하기
//       </div>
//     </div>
//   </div>
//   <div className={styles.list_item}>
//     <img src={missKorea} alt="img6" className={styles.image} />
//     <div className={styles.middle}>
//       <div>제 91회 춘향제</div>
//       <br />
//       <div>2022.03.03 ~ 2022.05.10</div>
//     </div>
//     <div className={styles.bottom}>
//       <div
//         className={styles.button}
//         onClick={() => {
//           navigate("/poll/:pollnum");
//         }}
//       >
//         투표하기
//       </div>
//     </div>
//   </div>
//   <div className={styles.list_item}>
//     <img src={mrKorea} alt="img7" className={styles.image} />
//     <div className={styles.middle}>
//       <div>제 91회 춘향제</div>
//       <br />
//       <div>2022.03.03 ~ 2022.05.10</div>
//     </div>
//     <div className={styles.bottom}>
//       <div
//         className={styles.button}
//         onClick={() => {
//           navigate("/poll/:pollnum");
//         }}
//       >
//         투표하기
//       </div>
//     </div>
//   </div>
//   <div className={styles.list_item}>
//     <img src={fox} alt="img8" className={styles.image} />
//     <div className={styles.middle}>
//       <div>제 91회 춘향제</div>
//       <br />
//       <div>2022.03.03 ~ 2022.05.10</div>
//     </div>
//     <div className={styles.bottom}>
//       <div
//         className={styles.button}
//         onClick={() => {
//           navigate("/poll/:pollnum");
//         }}
//       >
//         투표하기
//       </div>
//     </div>
//   </div>
//   <div className={styles.list_item}>
//     <img src={snack2} alt="img10" className={styles.image} />
//     <div className={styles.middle}>
//       <div>제 91회 춘향제</div>
//       <br />
//       <div>2022.03.03 ~ 2022.05.10</div>
//     </div>
//     <div className={styles.bottom}>
//       <div
//         className={styles.button}
//         onClick={() => {
//           navigate("/poll/:pollnum");
//         }}
//       >
//         투표하기
//       </div>
//     </div>
//   </div>
//   <div className={styles.list_item}>
//     <img src={hair} alt="img11" className={styles.image} />
//     <div className={styles.middle}>
//       <div>제 91회 춘향제</div>
//       <br />
//       <div>2022.03.03 ~ 2022.05.10</div>
//     </div>
//     <div className={styles.bottom}>
//       <div
//         className={styles.button}
//         onClick={() => {
//           navigate("/poll/:pollnum");
//         }}
//       >
//         투표하기
//       </div>
//     </div>
//   </div>
//   <div className={styles.list_item}>
//     <img src={snack} alt="img9" className={styles.image} />
//     <div className={styles.middle}>
//       <div>제 91회 춘향제</div>
//       <br />
//       <div>2022.03.03 ~ 2022.05.10</div>
//     </div>
//     <div className={styles.bottom}>
//       <div
//         className={styles.button}
//         onClick={() => {
//           navigate("/poll/:pollnum");
//         }}
//       >
//         투표하기
//       </div>
//     </div>
//   </div>
// </div>

const itemData = [
  {
    img: chunhyang,
    title: "chunhyang",
  },
  {
    img: ocean,
    title: "ocean",
  },
  {
    img: ev9,
    title: "ev9",
  },
  {
    img: kClassic,
    title: "kClassic",
  },
  {
    img: logoContest,
    title: "logoContest",
  },
  {
    img: missKorea,
    title: "missKorea",
  },
  {
    img: eyes,
    title: "eyes",
  },
  {
    img: fox,
    title: "fox",
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
