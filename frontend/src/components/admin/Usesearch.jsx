import Styles from "./Usesearch.module.css";
import * as React from 'react';
import PropTypes from 'prop-types';
import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Table from '@mui/material/Table';
import TableHead from '@mui/material/TableHead';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableFooter from '@mui/material/TableFooter';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import IconButton from '@mui/material/IconButton';
import FirstPageIcon from '@mui/icons-material/FirstPage';
import KeyboardArrowLeft from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRight from '@mui/icons-material/KeyboardArrowRight';
import LastPageIcon from '@mui/icons-material/LastPage';
import { styled } from '@mui/system';

function TablePaginationActions(props) {
    const theme = useTheme();
    const { count, page, rowsPerPage, onPageChange } = props;
  
    const handleFirstPageButtonClick = (event) => {
      onPageChange(event, 0);
    };
  
    const handleBackButtonClick = (event) => {
      onPageChange(event, page - 1);
    };
  
    const handleNextButtonClick = (event) => {
      onPageChange(event, page + 1);
    };
  
    const handleLastPageButtonClick = (event) => {
      onPageChange(event, Math.max(0, Math.ceil(count / rowsPerPage) - 1));
    };
  
    return (
      <Box sx={{ flexShrink: 0, ml: 2.5 }}>
        <IconButton
          onClick={handleFirstPageButtonClick}
          disabled={page === 0}
          aria-label="first page"
        >
          {theme.direction === 'rtl' ? <LastPageIcon /> : <FirstPageIcon />}
        </IconButton>
        <IconButton
          onClick={handleBackButtonClick}
          disabled={page === 0}
          aria-label="previous page"
        >
          {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
        </IconButton>
        <IconButton
          onClick={handleNextButtonClick}
          disabled={page >= Math.ceil(count / rowsPerPage) - 1}
          aria-label="next page"
        >
          {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
        </IconButton>
        <IconButton
          onClick={handleLastPageButtonClick}
          disabled={page >= Math.ceil(count / rowsPerPage) - 1}
          aria-label="last page"
        >
          {theme.direction === 'rtl' ? <FirstPageIcon /> : <LastPageIcon />}
        </IconButton>
      </Box>
    );
  }
  
  TablePaginationActions.propTypes = {
    count: PropTypes.number.isRequired,
    onPageChange: PropTypes.func.isRequired,
    page: PropTypes.number.isRequired,
    rowsPerPage: PropTypes.number.isRequired,
  };
  
  function createData(name, calories, fat) {
    return { name, calories, fat };
  }
  
  const rows = [
    createData('Cupcake', 305, 3.7),
    createData('Donut', 452, 25.0),
    createData('Eclair', 262, 16.0),
    createData('Frozen yoghurt', 159, 6.0),
    createData('Gingerbread', 356, 16.0),
    createData('Honeycomb', 408, 3.2),
    createData('Ice cream sandwich', 237, 9.0),
    createData('Jelly Bean', 375, 0.0),
    createData('KitKat', 518, 26.0),
    createData('Lollipop', 392, 0.2),
    createData('Marshmallow', 318, 0),
    createData('Nougat', 360, 19.0),
    createData('Oreo', 437, 18.0),
  ].sort((a, b) => (a.calories < b.calories ? -1 : 1));
  
  const div = styled('div')`
  table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 50vw;
    height: 40vh;
    margin-top: 22vh;
    margin-left: 20vw;
    position: fixed; 
  }

  td,
  th {
    border: 1px solid #ddd;
    text-align: left;
    padding: 8px;
    height: 2vh;
  }

  th {
    background-color: #e8eca559;
  }
`;

function UserSearch() {

    // const useStyles = makeStyles({
    //     tableCell : {
    //         height: "10vh",
    //     }
    // })

    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(5);

    // Avoid a layout jump when reaching the last page with empty rows.
    const emptyRows =
        page > 0 ? Math.max(0, (1 + page) * rowsPerPage - rows.length) : 0;

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    return(
        <>
            <input className={Styles.userEmail} type="text" placeholder="회원 이메일" />
            <button className={Styles.searchbtn}></button>
            <div>
            <TableContainer component={Paper} style={{width:'48vw', height:'50vh', marginTop:'45vh', marginLeft: '25vw', position: 'fixed' }}>
                {/* <Table sx={{ minWidth: '20vw' }} aria-label="custom pagination table"> */}
                <Table style={{width:'45vw', top:'50vh'}}  aria-label="custom pagination table">
                    <TableHead>
                        <TableRow>
                            <TableCell align="center" style={{fontWeight:'bold'}}>
                                Nickname
                            </TableCell>
                            <TableCell align="center" style={{fontWeight:'bold'}}>
                                E-mail
                            </TableCell>
                            <TableCell align="center" style={{fontWeight:'bold'}}>
                                1:1 문의 여부
                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody style={{height:'3vh' }}>
                        {(rowsPerPage > 0
                            ? rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            : rows
                        ).map((row) => (
                            <TableRow key={row.name}>
                                <TableCell component="th" style={{ width: '15vw'}} align="center">
                                {/* <TableCell component="th" align="center"> */}
                                    {row.name}
                                </TableCell>
                                <TableCell style={{ width: '15vw' }} align="center">
                                {/* <TableCell align="center"> */}
                                    {row.calories}
                                </TableCell>
                                <TableCell style={{ width: '15vw' }} align="center">
                                {/* <TableCell align="center"> */}
                                    {row.fat}
                                </TableCell>
                            </TableRow>
                        ))}

                        {emptyRows > 0 && (
                            <TableRow style={{ height: '30vh' * emptyRows }}>
                                <TableCell colSpan={6} />
                            </TableRow>
                        )}
                    </TableBody>
                    <TableFooter>
                        <TableRow>
                            <TablePagination
                            rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
                            colSpan={3}
                            count={rows.length}
                            rowsPerPage={rowsPerPage}
                            page={page}
                            SelectProps={{
                                inputProps: {
                                'aria-label': 'rows per page',
                                },
                                native: true,
                            }}
                            onPageChange={handleChangePage}
                            onRowsPerPageChange={handleChangeRowsPerPage}
                            ActionsComponent={TablePaginationActions}
                            />
                        </TableRow>
                    </TableFooter>
                </Table>
            </TableContainer>
            </div>
            {/* <div>
                <Table className={Styles.table}>
                    <thead className={Styles.thead}>
                        <tr>
                            <th>#</th>
                            <th>Nickname</th>
                            <th>E-mail</th>
                            <th>휴대폰 인증여부</th>
                        </tr>
                    </thead>
                    <tbody className={Styles.tbody}>
                        <tr>
                            <td>{""}1</td>
                            <td>신짱아 짱</td>
                            <td>ssafy@ssafy</td>
                            <td>인증</td>
                         </tr>
                    </tbody>
                </Table>
            </div> */}
        </>
    )
}
export default UserSearch;