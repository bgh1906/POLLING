import styles from "./Admin.module.css";
import { Link, useNavigate } from "react-router-dom";
import NewNav from "../components/layout/NewNav.jsx";
import { useState, useEffect } from "react";
import Footer from "../components/layout/Footer";
import Grid from "@mui/material/Grid";
import Moment from "react-moment";
import logo from "../assets/mark_slim.png";
import Button from "@mui/material/Button";
import axios from "axios";
import { registerBlock, getStartIndexBlock } from "../contracts/CallContract";

function Admin() {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const navigate = useNavigate();
  const [polldata, setPolldata] = useState([]);
  const [polldata2, setPolldata2] = useState([]);
  const [polldata3, setPolldata3] = useState([]);
  const [polldata4, setPolldata4] = useState([]);
  const [rendernumber, setRendernumber] = useState(0);
  const token = sessionStorage.getItem("token");
  const wallet = sessionStorage.getItem("wallet");
  useEffect(() => {

    axios
      .get("https://j6a304.p.ssafy.io/api/polls/unapproved/0/50")
      .then((res) => {
        setPolldata(res.data);
      })
      .catch((error) => {
        console.log(error.response);
      });
    axios
      .get("https://j6a304.p.ssafy.io/api/polls/wait/0/50")
      .then((res) => {
        setPolldata2(res.data);
      })
      .catch((error) => {
        console.log(error.response);
      });

    axios
      .get("https://j6a304.p.ssafy.io/api/polls/progress/0/50")
      .then((res) => {
        setPolldata3(res.data);
      })
      .catch((error) => {
        console.log(error.response);
      });

    axios
      .get("https://j6a304.p.ssafy.io/api/polls/done/0/50")
      .then((res) => {
        setPolldata4(res.data);
      })
      .catch((error) => {
        console.log(error.response);
      });
  }, [rendernumber]);

  async function changeStatuswait(e) {
    const poll_id = e.target.name;
    const res = await axios.get(
      `https://j6a304.p.ssafy.io/api/polls/admin/${poll_id}`,
      {
        headers: {
          Authorization: token,
        },
      }
    );
    const nomineeNum = res.data.candidates.length;
    console.log("등록할 후보 수는 :", nomineeNum);
    await registerBlock(nomineeNum);
    const Index = await getStartIndexBlock(wallet);
    console.log("시작 Index값은 :", Index);
    let candIdx = [];
    for (let i = parseInt(Index); i < parseInt(Index) + nomineeNum; i++) {
      candIdx.push(i);
    }
    console.log("서버에 넘겨줄 후보자 인덱스 리스트 :", candIdx);
    axios
      .patch(
        `https://j6a304.p.ssafy.io/api/polls/admin/wait`,
        {
          listCandidateIndex: candIdx,
          pollId: poll_id,
        },
        {
          headers: {
            Authorization: token,
          },
        }
      )
      .then(() => {
        setRendernumber(rendernumber + 1);
      });
  }

  function moveToUpdate(e) {
    const poll_id = e.target.name;
    navigate(`/poll/update/${poll_id}`);
  }

  function changePollOption(e) {
    const poll_id = e.target.name;
    axios
      .patch(
        `https://j6a304.p.ssafy.io/api/polls/admin/open/${poll_id}`,
        {},
        {
          headers: {
            Authorization: token,
          },
        }
      )
      .then(() => {
        setRendernumber(rendernumber + 1);
        console.log("투표 옵션 변경!");
      });
  }

  return (
    <div>
      <NewNav />
      {/* Header */}
      <div id={styles.title_header}>
        <div className={styles.title}>
          <div>
            <div style={{ textAlign: "center" }}>POLL</div>
            <div>MANAGEMENT </div>
          </div>
        </div>

        <div className={styles.create}>
          <Link to="/createpoll" className={styles.create}>
            CREATE A POLL
          </Link>
        </div>
        <Moment id={styles.moment}></Moment>
      </div>

      <div className={styles.container}>
        <div style={{ width: "100%" }}>
          <div id={styles.status}>Poll Status</div>
          <img id={styles.logo} src={logo} alt="logo" />

          {/* unapproved */}
          <div id={styles.status_title}>
            UNAPPROVED
            <div id={styles.status_kor}> 미승인 </div>
          </div>
          <Grid id={styles.poll_container} container>
            {polldata.map((poll) => (
              <Grid
                key={poll.pollId}
                id={styles.pollbox}
                item
                xs={12}
                sm={6}
                lg={3}
              >
                <img id={styles.list_img} src={poll.thumbnail} alt="main" />
                <div id={styles.list_pollname}> {poll.title} </div>
                <div id={styles.list_datefont}>
                  {" "}
                  시작: {poll.startDate} <br />
                  종료: {poll.endDate}{" "}
                </div>
                <div>
                  <Button
                    id={styles.status_button1}
                    onClick={moveToUpdate}
                    name={poll.pollId}
                    variant="contained"
                  >
                    투표 수정하기
                  </Button>
                </div>
                <div>
                  <Button
                    id={styles.status_button2}
                    onClick={changeStatuswait}
                    name={poll.pollId}
                    variant="contained"
                  >
                    투표 승인하기
                  </Button>
                </div>
              </Grid>
            ))}
          </Grid>
          <div id={styles.status_under}></div>
          {/* stand by */}
          <div id={styles.status_title}>
            STAND BY
            <div id={styles.status_kor}> 대기 </div>
          </div>
          <Grid id={styles.poll_container} container>
            {polldata2.map((poll) => (
              <Grid
                key={poll.pollId}
                id={styles.pollbox}
                item
                xs={12}
                sm={6}
                lg={3}
              >
                <img id={styles.list_img} src={poll.thumbnail} alt="main" />
                <div id={styles.list_pollname}> {poll.title} </div>
                <div id={styles.list_datefont}>
                  {" "}
                  시작: {poll.startDate} <br />
                  종료: {poll.endDate}{" "}
                </div>
                {poll.openStatus ? (
                  <div id={styles.list_datefont}> 실시간 투표수 공개</div>
                ) : (
                  <div id={styles.list_datefont}> 실시간 투표수 비공개</div>
                )}
                <div>
                  <Button
                    id={styles.status_button2}
                    onClick={changePollOption}
                    name={poll.pollId}
                    variant="contained"
                  >
                    투표 옵션변경{" "}
                  </Button>
                </div>
              </Grid>
            ))}
          </Grid>
          <div id={styles.status_under}></div>
          {/* In progress */}
          <div id={styles.status_title}>
            IN PROGRESS
            <div id={styles.status_kor}> 진행중 </div>
          </div>
          <Grid id={styles.poll_container} container>
            {polldata3.map((poll) => (
              <Grid
                key={poll.pollId}
                id={styles.pollbox}
                item
                xs={12}
                sm={6}
                lg={3}
              >
                <img id={styles.list_img} src={poll.thumbnail} alt="main" />
                <div id={styles.list_pollname}> {poll.title} </div>
                <div id={styles.list_datefont}>
                  {" "}
                  시작: {poll.startDate} <br />
                  종료: {poll.endDate}{" "}
                </div>
                {poll.openStatus ? (
                  <div id={styles.list_datefont}> 실시간 투표수 공개</div>
                ) : (
                  <div id={styles.list_datefont}> 실시간 투표수 비공개</div>
                )}
                <div>
                  <Button
                    id={styles.status_button2}
                    onClick={changePollOption}
                    name={poll.pollId}
                    variant="contained"
                  >
                    투표 옵션변경
                  </Button>
                </div>
              </Grid>
            ))}
          </Grid>
          <div id={styles.status_under}></div>
          {/* history */}
          <div id={styles.status_title}>
            {" "}
            CLOSED
            <div id={styles.status_kor}> 종료 </div>
          </div>
          <Grid id={styles.poll_container} container>
            {polldata4.map((poll) => (
              <Grid
                key={poll.pollId}
                id={styles.pollbox}
                item
                xs={12}
                sm={6}
                lg={3}
              >
                <img id={styles.list_img} src={poll.thumbnail} alt="main" />
                <div id={styles.list_pollname}> {poll.title} </div>
                <div id={styles.list_datefont}>
                  {" "}
                  시작: {poll.startDate} <br />
                  종료: {poll.endDate}{" "}
                </div>
                <div>
                  <Button id={styles.status_button1} variant="contained">
                    NFT 발급
                  </Button>
                </div>
                <div>
                  <Button id={styles.status_button2} variant="contained">
                    HISTORY 추가
                  </Button>
                </div>
              </Grid>
            ))}
          </Grid>
          <div id={styles.status_under}></div>
        </div>
      </div>

      <Footer></Footer>
    </div>
  );
}

export default Admin;
