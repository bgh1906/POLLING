import NewNav from "../layout/NewNav";
import { styled } from '@mui/system';
import TablePaginationUnstyled from '@mui/base/TablePaginationUnstyled';
import { useState } from "react";
import { Link } from "react-router-dom";
import Qna from "./Qnawrite";
import * as React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionDetails from '@mui/material/AccordionDetails';
import AccordionSummary from '@mui/material/AccordionSummary';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Styles from './Qnalist.module.css';

function QnaList2() {

    const [expanded, setExpanded] = React.useState(false);

    const handleChange = (panel) => (event, isExpanded) => {
      setExpanded(isExpanded ? panel : false);
    };

    return (
        <>
            {/* <Accordion style={{width:"45vw", left:"8vw"}} expanded={expanded === 'panel1'} onChange={handleChange('panel1')}> */}
            <Accordion className={Styles.accordion} expanded={expanded === 'panel1'} onChange={handleChange('panel1')}>
              <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls="panel1bh-content"
                id="panel1bh-header"
              >
                <Typography className={Styles.typotype} sx={{ width: '33%', flexShrink: 0 }}>
                  contactType
                </Typography>
                <Typography className={Styles.typoTitle} sx={{ color: 'text.secondary' }}>(company,poll,ticket)</Typography>
              </AccordionSummary>
              <AccordionDetails>
                <Typography className={Styles.typoTitle} >
                  blahblahblahblahblahblahblahblahblahblah
                </Typography>
              </AccordionDetails>
            </Accordion>
        </>
    );
}

export default QnaList2;