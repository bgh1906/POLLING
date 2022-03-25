import * as React from 'react';
import { styled } from '@mui/system';
import TablePaginationUnstyled from '@mui/base/TablePaginationUnstyled';
import Styles from "./Usesearch.module.css";
import NavbarToggle from 'react-bootstrap/esm/NavbarToggle';

function createData(name, calories, fat) {
  return { name, calories, fat };
}

const rows = [
  createData('사랑해요신짱아', 'shinjja@naver.com', 3.7),
  createData('오마이걸짱', 'loveomg@omg.com', 25.0),
  createData('아이유아니유', 'iuau@iuana.com', 16.0),
  createData('갓구아짱', 'guajjang@gua', 6.0),
  createData('이부장님', 'bujanglee@ssafy.com', 16.0),
  createData('서대리', 'whydearee@ssafy.com', 3.2),
  createData('배수지환', 'BsuzyH@ssafy.com', 9.0),
  createData('저스트 비버', 'fakebieber@singer.com', 0.0),
  createData('능력주헌', 'superjung@ssafy', 26.0),
  createData('승진원츄', 'teamkim@ssafy.com', 0.2),
  createData('Marshmallow', 318, 0),
  createData('Nougat', 360, 19.0),
  createData('Oreo', 437, 18.0),
];
// ].sort((a, b) => (a.calories < b.calories ? -1 : 1));

const Root = styled('div')`
  table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    box-shadow: 0.2vw 0.1vw 0.2vw 0.1vw #babb9536;
    width: 60vw;
    height: 40vh;
    margin-top: 20vh;
    margin-left: 20vw;
    position: fixed;
  }

  td,
  th {
    border: 1px solid #ddd;
    text-align: left;
    padding: 8px;
    height: 2vh;
    font-family: 'GangwonEdu_OTFBoldA';
    font-weight: 400;
    font-size: 2.5vh; 
  }

  th {
    background-color: #e8eca559;
    font-family: 'RussoOne';
  }
`;

const CustomTablePagination = styled(TablePaginationUnstyled)`
  & .MuiTablePaginationUnstyled-toolbar {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;

    @media (min-width: 768px) {
      flex-direction: row;
      align-items: center;
    }
  }

  & .MuiTablePaginationUnstyled-selectLabel {
    margin: 0;
  }

  & .MuiTablePaginationUnstyled-displayedRows {
    margin: 0;

    @media (min-width: 768px) {
      margin-left: auto;
    }
  }

  & .MuiTablePaginationUnstyled-spacer {
    display: none;
  }

  & .MuiTablePaginationUnstyled-select {
    background-color: #e8eca000;
  }

  & .MuiTablePaginationUnstyled-actions {
    display: flex;
    gap: 0.25rem;
    background-color: #e8eca000;
  }
`;

export default function UserSearch2() {
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

  return (
    <Root>
      <div style={{top: '30vh'}}>
        <div>
          <input className={Styles.userEmail} type="text" placeholder="회원 이메일"/>
          <img src='https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/20/000000/external-email-advertising-kiranshastry-gradient-kiranshastry-7.png' className={Styles.emailicon}></img>
          {/* <button className={Styles.searchbtn}></button> */}
        </div>
        <img src='https://img.icons8.com/pastel-glyph/30/000000/search--v2.png' className={Styles.searchbtn}></img>
        <table style={{top: '30vh'}} aria-label="custom pagination table">
          <thead>
            <tr>
              <th>Nickname</th>
              <th>E-mail</th>
              <th>???</th>
            </tr>
          </thead>
          <tbody>
            {(rowsPerPage > 0
              ? rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              : rows
            ).map((row) => (
              <tr key={row.name}>
                <td style={{ width: '10vw' }}>{row.name}</td>
                <td style={{ width: '14vw' }} align="right">
                  {row.calories}
                </td>
                <td style={{ width: '10vw' }} align="right">
                  {row.fat}
                </td>
              </tr>
            ))}

            {emptyRows > 0 && (
              <tr style={{ height: 41 * emptyRows }}>
                <td colSpan={3} />
              </tr>
            )}
          </tbody>
          <tfoot>
            <tr>
              <CustomTablePagination
                rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
                colSpan={3}
                count={rows.length}
                rowsPerPage={rowsPerPage}
                page={page}
                componentsProps={{
                  select: {
                    'aria-label': 'rows per page',
                  },
                  actions: {
                    showFirstButton: true,
                    showLastButton: true,
                  },
                }}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
              />
            </tr>
          </tfoot>
        </table>
      </div>
    </Root>
  );
}
