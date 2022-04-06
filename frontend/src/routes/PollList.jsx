import Footer from "../components/layout/Footer";
import styles from "./PollList.module.css";
import { useNavigate } from "react-router-dom";
import Newnav from "../components/layout/NewNav";
import Box from "@mui/material/Box";
import ImageList from "@mui/material/ImageList";
import ImageListItem from "@mui/material/ImageListItem";
import Button from "@mui/material/Button";
import { useMediaQuery } from "react-responsive";
import { useEffect, useState } from "react";
import axios from "axios";

export default function PollList() {
  const navigate = useNavigate();

  const [itemData, setItemData] = useState([]);

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  useEffect(() => {
    axios
      .get("https://j6a304.p.ssafy.io/api/polls/progress/0/50")
      .then((res) => {
        console.log(res);
        setItemData(res.data);
      })
      .catch((error) => {
        console.log(error.response);
      });
  }, []);

  function shuffle(array) {
    for (let i = array.length - 1; i > 0; i--) {
      let j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
  }
  shuffle(itemData);
  // useEffect(() => {
  // }, [itemData]);
  const isDesktop = useMediaQuery({ minWidth: 1200 });
  const isLabtop = useMediaQuery({ minWidth: 992, maxWidth: 1199 });
  const isTablet = useMediaQuery({ minWidth: 768, maxWidth: 991 });
  const isMobile = useMediaQuery({ maxWidth: 767 });

  return (
    <>
      <Newnav />
      <div className={styles.polllist}>
        <div className={styles.list_title}>Poll List</div>
        <Box sx={{ width: "80vw", height: "auto" }}>
          {isDesktop && (
            <ImageList variant="masonry" cols={4} gap={28}>
              {itemData.map((item, index) => (
                <ImageListItem key={index} className={styles.opened_item}>
                  <img
                    // src={`${item.thumbnail}?w=248&fit=crop&auto=format`}
                    src={item.thumbnail}
                    srcSet={`${item.thumbnail}?w=248&fit=crop&auto=format&dpr=2 2x`}
                    alt={item.title}
                    loading="lazy"
                    className={styles.opened_img}
                  />
                  <div className={styles.opened_info}>
                    <div style={{ fontSize: "2vw", fontWeight: 900 }}>
                      {item.title}
                    </div>
                    <div id={styles.poll_date}>
                      시작: {item.startDate}
                      <br />
                      종료: {item.endDate}
                    </div>
                  </div>
                  <div className={styles.opened_button}>
                    <Button
                      id={styles.poll_button}
                      variant="contained"
                      onClick={() => {
                        navigate(`/poll/${item.pollId}`);
                      }}
                    >
                      투표하기
                    </Button>
                  </div>
                </ImageListItem>
              ))}
            </ImageList>
          )}
          {isLabtop && (
            <ImageList variant="masonry" cols={3} gap={28}>
              {itemData.map((item, index) => (
                <ImageListItem key={index} className={styles.opened_item}>
                  <img
                    src={`${item.img}?w=248&fit=crop&auto=format`}
                    srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
                    alt={item.title}
                    loading="lazy"
                    className={styles.opened_img}
                  />
                  <div className={styles.opened_info}>
                    <div>{item.title}</div>
                    <div id={styles.poll_date}>
                      시작: {item.startDate}
                      <br />
                      종료: {item.endDate}
                    </div>
                  </div>
                  <div className={styles.opened_button}>
                    <Button
                      id={styles.poll_button}
                      variant="contained"
                      onClick={() => {
                        navigate(`/poll/${item.pollId}`);
                      }}
                    >
                      투표하기
                    </Button>
                  </div>
                </ImageListItem>
              ))}
            </ImageList>
          )}
          {isTablet && (
            <ImageList variant="masonry" cols={2} gap={28}>
              {itemData.map((item, index) => (
                <ImageListItem key={index} className={styles.opened_item}>
                  <img
                    src={`${item.img}?w=248&fit=crop&auto=format`}
                    srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
                    alt={item.title}
                    loading="lazy"
                    className={styles.opened_img}
                  />
                  <div className={styles.opened_info}>
                    <div>{item.title}</div>
                    <div id={styles.poll_date}>
                      시작: {item.startDate}
                      <br />
                      종료: {item.endDate}
                    </div>
                  </div>
                  <div className={styles.opened_button}>
                    <Button
                      id={styles.poll_button}
                      variant="contained"
                      onClick={() => {
                        navigate(`/poll/${item.pollId}`);
                      }}
                    >
                      투표하기
                    </Button>
                  </div>
                </ImageListItem>
              ))}
            </ImageList>
          )}
          {isMobile && (
            <ImageList variant="masonry" cols={1} gap={28}>
              {itemData.map((item, index) => (
                <ImageListItem key={index} className={styles.opened_item}>
                  <img
                    src={`${item.img}?w=248&fit=crop&auto=format`}
                    srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
                    alt={item.title}
                    loading="lazy"
                    className={styles.opened_img}
                  />
                  <div className={styles.opened_info}>
                    <div>{item.title}</div>
                    <div id={styles.poll_date}>
                      시작: {item.startDate}
                      <br />
                      종료: {item.endDate}
                    </div>
                  </div>
                  <div className={styles.opened_button}>
                    <Button
                      id={styles.poll_button}
                      variant="contained"
                      onClick={() => {
                        navigate(`/poll/${item.pollId}`);
                      }}
                    >
                      투표하기
                    </Button>
                  </div>
                </ImageListItem>
              ))}
            </ImageList>
          )}
        </Box>
      </div>
      <Footer />
    </>
  );
}
