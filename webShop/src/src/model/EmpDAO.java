
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

// DAO(Data Access Object)
public class EmpDAO {
	
	// CRUD(Create:insert, Read:select, U:Update, D:Delete)
	
	public EmpVO loginChk(int empid, String email) {
		EmpVO emp = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where employee_id = ? and email = ? ";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			st.setString(2, email);
			rs = st.executeQuery();
			while(rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		return emp;
	}
	
	
	
	
	public List<JobVO> selectAllJobs() {
		List<JobVO> joblist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from jobs order by 1 ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				JobVO job = new JobVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				joblist.add(job);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		
		return joblist;
	}
	
	
	
	
	
	
	
	// 사용자가 웹을 통해서 삭제
	public int deleteEmp(int empid) {
		int result = 0;
		String sql = "delete from employees where EMPLOYEE_ID = ?";
		Connection conn;
		PreparedStatement st = null;
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(null, st, conn);
		}
		return result;
		
	}
	
	
	
	// 사용자가 웹을 통해서 개인정보수정, 원 정보가 보인다 => controller(입력을 받음) => DAO => DB
	public int updateEmp(EmpVO emp) {
		int result = 0;
		String sql = 
				" update employees set " +
			    " FIRST_NAME = ?, " +
			    " LAST_NAME = ?, " +
			    " EMAIL = ?, " +          
			    " PHONE_NUMBER = ?, " +    
			    " HIRE_DATE = ?, " +      
			    " JOB_ID = ?, " +         
			    " SALARY = ?, " +               
			    " COMMISSION_PCT = ?, " +        
			    " MANAGER_ID = ?, " +             
			    " DEPARTMENT_ID = ? " +   
			" where EMPLOYEE_ID = ? ";
		
		Connection conn;
		PreparedStatement st = null;
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(11, emp.getEmployee_id());
			st.setString(1, emp.getFirst_name());
			st.setString(2, emp.getLast_name());
			st.setString(3, emp.getEmail());
			st.setString(4, emp.getPhone_number());
			st.setDate(5, emp.getHire_date());
			st.setString(6, emp.getJob_id());
			st.setInt(7, emp.getSalary());
			st.setDouble(8, emp.getCommission_pct());
			st.setInt(9, emp.getManager_id());
			st.setInt(10, emp.getDepartment_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
	// 사용자가 웹을 통해서 회원가입 => controller(입력을 받음) => DAO => DB
	public int insertEmp(EmpVO emp) { // 사용자에게 받은 데이터가 들어와야 하는 부분!
		String sql = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?) ";
		Connection conn;
		PreparedStatement st = null;
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			st.setInt(1, emp.getEmployee_id());
			st.setString(2, emp.getFirst_name());
			st.setString(3, emp.getLast_name());
			st.setString(4, emp.getEmail());
			st.setString(5, emp.getPhone_number());
			st.setDate(6, emp.getHire_date());
			st.setString(7, emp.getJob_id());
			st.setInt(8, emp.getSalary());
			st.setDouble(9, emp.getCommission_pct());
			st.setInt(10, emp.getManager_id());
			st.setInt(11, emp.getDepartment_id());
			result = st.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBUtil.dbclose(null, st, conn);
		}
		
		return result;
	}
	
	
	// 1.  모든 직원 조회
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from employees";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		
		
		
		return emplist;
	}
	// 2. Primary Key -> null �Ұ�, �ʼ� Į��, �ߺ� ����
	public EmpVO selectById(int empid) {
		EmpVO emp = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where employee_id = ? ";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			rs = st.executeQuery();
			while(rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		return emp;
	}

	// 3. 부서로 조회
	public List<EmpVO> selectbyDept(int deptid) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where department_id = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, deptid);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		
		
		
		return emplist;
	}
	
	// 4. job_id�� ��ȸ
	public List<EmpVO> selectbyJob(String jobid) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where job_id = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, jobid);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		
		
		
		return emplist;
	}
	
	// 5. 급여로 조회
	public List<EmpVO> selectbySalary(int minsal, int maxsal) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where salary between ? and ? ";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, minsal);
			st.setInt(2, maxsal);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		
		
		
		return emplist;
	}
	
	// 6.  입사일로 조회
	public List<EmpVO> selectbyDate(String date1, String date2) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where hire_date between ? and ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, date1);
			st.setString(2, date2);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		
		
		
		return emplist;
	}
	
	
	public List<EmpVO> selectbyDate2(Date date1, Date date2) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where hire_date between ? and ?";
		try {
			st = conn.prepareStatement(sql);
			st.setDate(1, date1);
			st.setDate(2, date2);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		
		
		
		return emplist;
	}
	
	
	// 7. �̸��� Ư�� ���ڰ� �� ��� ��ȸ
	
	public List<EmpVO> selectbyFirstName(String ch) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where first_name like ? ";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, "%"+ch+"%");
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		
		
		
		return emplist;
	}
	// 8. ���� �������� ��ȸ (�μ�, job, salary, hire_date)
	public List<EmpVO> selectbyCondition(int deptid, String jobid, int sal, Date hdate) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees" +
				" where department_id = ?" +
				" and job_id = ?" +
				" and salary >= ? " +
				" and hire_date between ? and add_months(?, 24) ";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, deptid);
			st.setString(2, jobid);
			st.setInt(3, sal);
			st.setDate(4, hdate);
			st.setDate(5, hdate);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		
		
		
		return emplist;
	}
	
	
	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO();
		emp.setCommission_pct(rs.getDouble("Commission_pct"));
		emp.setDepartment_id(rs.getInt("Department_id"));
		emp.setEmail(rs.getString("Email"));
		emp.setEmployee_id(rs.getInt("Employee_id"));
		emp.setFirst_name(rs.getString("First_name"));
		emp.setHire_date(rs.getDate("Hire_date"));
		emp.setJob_id(rs.getString("Job_id"));
		emp.setLast_name(rs.getString("Last_name"));
		emp.setManager_id(rs.getInt("Manager_id"));
		emp.setPhone_number(rs.getString("Phone_number"));
		emp.setSalary(rs.getInt("Salary"));
		return emp;
	}

}
