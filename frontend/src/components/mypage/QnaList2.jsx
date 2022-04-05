import NewNav from "../layout/NewNav";
import { styled } from '@mui/system';
import TablePaginationUnstyled from '@mui/base/TablePaginationUnstyled';
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Qna from "./Qnawrite";
import * as React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionDetails from '@mui/material/AccordionDetails';
import AccordionSummary from '@mui/material/AccordionSummary';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Styles from './Qnalist.module.css';
import axios from "axios";

function QnaList2() {

    const token = sessionStorage.getItem("token")

    const [expanded, setExpanded] = React.useState(false);

    const handleChange = (panel) => (event, isExpanded) => {
      setExpanded(isExpanded ? panel : false);
    };

    //1:1문의 저장
    const [qnalist, setQnalist] = useState([]);

    // const getlist = () =>{
    //   axios
    //   .get(
    //     "https://j6a304.p.ssafy.io/api/contact"
    //   )
    //   .then((res) => {
    //     console.log(res);
    //     setQnalist(res.data);
    //   })
    //   .catch(error => {
    //     console.log("res",error.response);
    //     console.log("error",error);
    //   })
    // }

    useEffect(() => {
      // const getlist = () =>{
      let iscompomount = true;

      if( iscompomount === true ){
        axios
        .get(
          "https://j6a304.p.ssafy.io/api/contact",
          {
            headers: {
              "Authorization":token,
              // refreshToken: token,
            },
          }
        )
        .then((res) => {
          // console.log("data",res.data);
          setQnalist(res.data);
        })
        .catch(error => {
          console.log("res",error.response);
          console.log("error",error);
        })
      };
      return() => {
        iscompomount = false;
      };
    },[qnalist]);

    //1:1문의 삭제
    const qnadelet = (id) => {
      axios
        .delete(
          // `https://i6a306.p.ssafy.io:8080/api/contact/${id}`,
          `/api/contact/${id}`,
        //   {
        //     id: id,
        //     nickname: nickname,
        //     password: email,
        //     phoneNumber: title,
        // },
          {
              headers: {
                "Authorization":token,
                // refreshToken: token,
              },
          }
        )
        .then((res) => {
          console.log(res);
                  
        })
        .catch((e) => {
          // console.log(e);
          console.log("error,qnadelet",e);
          console.log("res,qnadelet",e.response);
      });
    }

    return (
        <>
          {/* <button></button> */}
            {/* <Accordion style={{width:"45vw", left:"8vw"}} expanded={expanded === 'panel1'} onChange={handleChange('panel1')}> */}
            {/* {(qnalist).map((qna) => ( */}
            {qnalist.map((index, key) => (
              <div key={index.id}>
                <Accordion 
                  className={Styles.accordion}
                  style={{marginBottom:'0vh', width:'45vw', left:'8vw', fontFamily:'GangwonEdu_OTFBoldA', backgroundColor:'rgba(250, 235, 215, 0.541)'}} 
                  expanded={expanded === `panel${index.id}`} 
                  onChange={handleChange(`panel${index.id}`)}
                  key={index}
                >
                  <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    // aria-controls="panel${index.id}bh-content"
                    // id="panel${index.id}bh-header"
                    key={key}
                  >
                    <div 
                      key={index.contactType}
                      style={{fontFamily:'GangwonEdu_OTFBoldA', fontSize:'1.4vw', lineHeight:'4vh'}} 
                      className={Styles.typotype} 
                      sx={{ width: '33%', flexShrink: 0 }}
                    >
                      [ {index.contactType.slice(8, 14)} ]
                    </div>
                    <div 
                      key={index.title}
                      maxLength="20"
                      style={{fontFamily:'GangwonEdu_OTFBoldA', fontSize:'1.3vw', paddingLeft:'5vw', fontWeight:'5vh'}}  
                      className={Styles.typoTitle} 
                      sx={{ color: 'text.secondary' }}
                    >
                      {index.title}
                    </div>
                  </AccordionSummary>
                  <AccordionDetails key={index}>
                    <div 
                      key={index.content} 
                      className={Styles.typoTitle} 
                      style={{wordBreak:'break-all', fontFamily:'GangwonEdu_OTFBoldA', fontSize:'1.3vw'}} 
                    >
                      {index.content}
                    </div>
                    <button 
                      key={index.id} 
                      value={index.id} 
                      onClick={() => {qnadelet(index.id)}} 
                      className={Styles.listbtn}
                      style={{fontFamily:'GangwonEdu_OTFBoldA', left:'39vw',borderRadius:'30vw', backgroundColor:'rgba(255, 194, 113, 0.452)'}} 
                    >
                      삭제
                    </button>
                  </AccordionDetails>
                </Accordion>
                {/* <input type={"checkbox"} /> */} 
                {/* <button value={index.id} onClick={ () => {qnadelet(index.id)}} className={Styles.listbtn}>
                  삭제
                </button> */}
              </div>
            ))}
            {/* ))} */}
        </>
    );
}

export default QnaList2;