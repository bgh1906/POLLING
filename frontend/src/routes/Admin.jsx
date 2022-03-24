import styles from "./Admin.module.css";
import { Link } from "react-router-dom";
import NewNav from "../components/layout/NewNav.jsx"
import { useState, useEffect } from "react";
import Footer from "../components/layout/Footer";
import Grid from '@mui/material/Grid';
import Moment from 'react-moment';
import logo from "../assets/mark_slim.png"
import Button from '@mui/material/Button';
import axios from "axios";



function Admin() {

    useEffect(()=>{
        window.scrollTo(0,0);
    }, [])


    const [polldata, setPolldata] = useState([]);

    useEffect(()=>{
            axios
            .get("http://j6a304.p.ssafy.io:8080/api/votes/25")
            .then((res) => {
                console.log("성공!");
                console.log(res);
                setPolldata(res.data);
            })
            .catch((e) =>{
                console.error(e);
            });
    }, []);
        
      

    return (
        <div>
              <NewNav />
{/* Header */}
            <div id={styles.title_header}>
                <div className={styles.title}>
                    <div >
                    <div style={{ textAlign:"center"}}>POLL</div>
                    <div>MANAGEMENT </div>
                    </div>
                    
                </div> 

                <div className={styles.create} >
                    <Link to="/createpoll" className={styles.create}>
                        CREATE A POLL
                    </Link>
                </div>
                <Moment id={styles.moment}></Moment>
            </div>

        <div className={styles.container}>
            <div style={{width:"100%"}}>
                <div id={styles.status}>POLL STATUS</div>
                <img id={styles.logo} src={logo} alt="logo" />

{/* unapproved */}
                <div id={styles.status_title}>UNAPPROVED
                    <div id={styles.status_kor}> 미승인 </div>
                </div>
                <Grid id={styles.poll_container} container>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 수정하기</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 승인하기</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 수정하기</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 승인하기</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 수정하기</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 승인하기</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 수정하기</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 승인하기</Button></div>
                    </Grid>
                </Grid>
                <div id={styles.status_under}></div>  
{/* stand by */}
                <div id={styles.status_title}>STAND BY
                    <div id={styles.status_kor}> 대기 </div>
                </div>
                <Grid id={styles.poll_container} container>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 수정하기</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 시작하기</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 수정하기</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 시작하기</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 수정하기</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 시작하기</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 수정하기</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 시작하기</Button></div>
                    </Grid>
                </Grid>
                <div id={styles.status_under}></div>
{/* In progress */}       
                <div id={styles.status_title}>IN PROGRESS
                    <div id={styles.status_kor}> 진행중 </div>
                </div>
                <Grid id={styles.poll_container} container>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 옵션변경</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 종료하기</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 옵션변경</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 종료하기</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 옵션변경</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 종료하기</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">투표 옵션변경</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">투표 종료하기</Button></div>
                    </Grid>
                </Grid>
                <div id={styles.status_under}></div>     
{/* history */}       
                <div id={styles.status_title}> CLOSED
                    <div id={styles.status_kor}> 종료 </div>
                </div>
                <Grid id={styles.poll_container} container>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">NFT 발급</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">HISTORY 추가</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">NFT 발급</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">HISTORY 추가</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">NFT 발급</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">HISTORY 추가</Button></div>
                    </Grid>
                    <Grid id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src="http://tong.visitkorea.or.kr/cms/resource/22/2655022_image2_1.jpg" alt="img1" />
                            <div> 제90회 전국춘향선발대회 </div>
                            <div> 2020.09.10 - 2020.09.13 </div>
                            <div><Button id={styles.status_button1} variant="contained">NFT 발급</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">HISTORY 추가</Button></div>
                    </Grid>
                </Grid>
                <div id={styles.status_under}></div>     
        </div>
</div>

        <Footer></Footer>
        </div>
    );
}

export default Admin;