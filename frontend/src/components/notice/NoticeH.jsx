//Accordion
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { useState } from 'react';
import Styles from "../../routes/Notice.module.css";

function NoticeH () {
    //accordian
    const [expanded, setExpanded] = useState(false);

    const handleChangeA = (panel) => (event, isExpanded) => {
        setExpanded(isExpanded ? panel : false);
    };


    return(

        <div>
            <Accordion expanded={expanded === 'panel1'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel1')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1bh-content"
                    id="panel1bh-header"
                >
                    <Typography style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        명예의 전당이란?
                    </Typography>
                    {/* <Typography sx={{ color: 'text.secondary' }}>I am an accordion</Typography> */}
                </AccordionSummary>
                <AccordionDetails>
                    <Typography style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            Nulla facilisi. Phasellus sollicitudin nulla et quam mattis feugiat.
                            Aliquam eget maximus est, id dignissim quam.
                    </Typography>
                </AccordionDetails>
            </Accordion>
            <Accordion expanded={expanded === 'panel2'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel2')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel2bh-content"
                    id="panel2bh-header"
                >
                    <Typography style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        명예의 전당 등록 기준
                    </Typography>
                    {/* <Typography sx={{ color: 'text.secondary' }}>I am an accordion</Typography> */}
                </AccordionSummary>
                <AccordionDetails>
                    <Typography style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            Nulla facilisi. Phasellus sollicitudin nulla et quam mattis feugiat.
                            Aliquam eget maximus est, id dignissim quam.
                    </Typography>
                </AccordionDetails>
            </Accordion>
            <Accordion expanded={expanded === 'panel3'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel3')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel3bh-content"
                    id="panel3bh-header"
                >
                    <Typography style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        이후 추가 예정 기능
                    </Typography>
                    {/* <Typography sx={{ color: 'text.secondary' }}>I am an accordion</Typography> */}
                </AccordionSummary>
                <AccordionDetails>
                    <Typography style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            Nulla facilisi. Phasellus sollicitudin nulla et quam mattis feugiat.
                            Aliquam eget maximus est, id dignissim quam.
                    </Typography>
                </AccordionDetails>
            </Accordion>
        </div>
    )
}

export default NoticeH;