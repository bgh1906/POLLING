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
          console.log("data",res.data);
          setQnalist(res.data);
        })
        .catch(error => {
          console.log("res",error.response);
          console.log("error",error);
        })
      // }
    },[]);

    //1:1문의 삭제
    const qnadelet = (id) => {
      axios
        .delete(
          `https://i6a306.p.ssafy.io:8080/api/contact/${id}`,
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
          console.log(e);
      });
    }

    return (
        <>
          {/* <button></button> */}
            {/* <Accordion style={{width:"45vw", left:"8vw"}} expanded={expanded === 'panel1'} onChange={handleChange('panel1')}> */}
            {/* {(qnalist).map((qna) => ( */}
            {qnalist.map((index, key) => (
              <div>
                <Accordion className={Styles.accordion} expanded={expanded === `panel${index.id}`} onChange={handleChange(`panel${index.id}`)}>
                  <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    // aria-controls="panel${index.id}bh-content"
                    // id="panel${index.id}bh-header"
                  >
                    <Typography key={index.contactType} className={Styles.typotype} sx={{ width: '33%', flexShrink: 0 }}>
                      {index.contactType.slice(8, 14)}
                    </Typography>
                    <Typography key={index.title} className={Styles.typoTitle} sx={{ color: 'text.secondary' }}>{index.title}</Typography>
                  </AccordionSummary>
                  <AccordionDetails>
                    <Typography key={index.content} className={Styles.typoTitle} >
                    {index.content}
                    </Typography>
                    <button value={index.id} onClick={ () => {qnadelet(index.id)}} className={Styles.listbtn}>
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