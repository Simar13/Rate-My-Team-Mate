package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.ConnectionSetter;
import bean.Customer;
import bean.Rating;
import bean.TeamMate;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.util.Tables;

import dao.CustomerDao;

public class CustomerDaoImpl implements CustomerDao {

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	String query, error;

	
	 static AmazonDynamoDBClient dynamoDB;

	    
	    private static void init() throws Exception {
	        /*
	         * The ProfileCredentialsProvider will return your [default]
	         * credential profile by reading from the credentials file located at
	         * (C:\\Users\\Prince\\.aws\\credentials).
	         */
	        AWSCredentials credentials = null;
	        try {
	            credentials = new ProfileCredentialsProvider("default").getCredentials();
	        } catch (Exception e) {
	            throw new AmazonClientException(
	                    "Cannot load the credentials from the credential profiles file. " +
	                    "Please make sure that your credentials file is at the correct " +
	                    "location (C:\\Users\\Prince\\.aws\\credentials), and is in valid format.",
	                    e);
	        }
	        dynamoDB = new AmazonDynamoDBClient(credentials);
	        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
	        dynamoDB.setRegion(usWest2);
	    }

	public ArrayList<Rating> getRatings(String firstName, String lastName, String university) {
		
		ArrayList<Rating> rating = new ArrayList<Rating>();
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	TeamMate c=new TeamMate();
        try {
	            String tableName = "rating";

	            // Create table if it does not exist yet
	            if (Tables.doesTableExist(dynamoDB, tableName)) {
	                System.out.println("Table " + tableName + " is already ACTIVE");
	            } else {
	                
	            	System.out.println("Table nahi hega");
	            }

	            DynamoDB dynam = new DynamoDB(dynamoDB);	            
	            Table table =  dynam.getTable(tableName);
	            HashMap<String, String> nameMap = new HashMap<String, String>();
	            nameMap.put("#us", "name");
	            nameMap.put("#uni", "university");
	           

	            HashMap<String, Object> valueMap = new HashMap<String, Object>();
	            valueMap.put(":usr",firstName+" "+lastName);
	            valueMap.put(":usr1",university);
	            
	            QuerySpec querySpec = new QuerySpec()
                .withKeyConditionExpression("#us = :usr and #uni = :usr1" )
                 .withNameMap(nameMap).withValueMap(valueMap);	            

	            ItemCollection<QueryOutcome> items = null;
	            Iterator<Item> iterator = null;
	            Item item = null;
          
                items = table.query(querySpec);
                iterator = items.iterator();
                while (iterator.hasNext()) {
                	
                    item = iterator.next();
                    
                    String coursecode=item.getString("coursecode");
                    String helpful=item.getString("helpful");
                    String knowledge=item.getString("knowledge");
                    String leader=item.getString("leader");
                    String participation=item.getString("participation");
                    String comment=item.getString("comment");
                    String averageRating=item.getString("averageRating");
                    String tag=item.getString("tag");
                    String [] tagArray = tag.split("--");
    				ArrayList<String> tags= new ArrayList<String>();
    				for (int i = 0; i < tagArray.length; i++) {
    				   tags.add(tagArray[i]);
    				}
    				Rating c = new Rating(1,coursecode,helpful,knowledge,leader,participation,tags,comment,averageRating);
    				rating.add(c);
                    System.out.println(item.toString());
                }
            }
            catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to AWS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException, which means the client encountered "
	                    + "a serious internal problem while trying to communicate with AWS, "
	                    + "such as not being able to access the network.");
	            System.out.println("Error Message: " + ace.getMessage());
	        }
		/*query = "select * from rating where firstname='"+firstName+"' and lastname='"+lastName+"' and university='"+university+"'";
		connection = ConnectionSetter.getConnection();
		try {
			statement = connection.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing retrieve: " + query);
			rs = statement.executeQuery(query);

			while (rs.next()) {
				int id =Integer.parseInt(rs.getString(1));
				String tag= rs.getString(7);
				String [] tagArray = tag.split("--");
				ArrayList<String> tags= new ArrayList<String>();
				for (int i = 0; i < tagArray.length; i++) {
				   tags.add(tagArray[i]);
				}
				Rating c = new Rating(id, rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), tags,rs.getString(8) ,rs.getString(9));
				System.out.println(c.getTag());
				rating.add(c);
			}

		} catch (SQLException e) {
			error = "Error in Retrieve :" + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
		} finally {
			close();
		}*/
		System.out.println(rating);
		return rating;
	}
	
	public Customer getCustomersByIdPwd(String userid, String pwd)
			throws SQLException {
		
			 try {
				init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        try {
		            String tableName = "customer";
	
		            // Create table if it does not exist yet
		            if (Tables.doesTableExist(dynamoDB, tableName)) {
		                System.out.println("Table " + tableName + " is already ACTIVE");
		            } else {
		                
		            	System.out.println("Table nahi hega");
		            }

		            DynamoDB dynam = new DynamoDB(dynamoDB);	            
		            Table table =  dynam.getTable(tableName);
		            HashMap<String, String> nameMap = new HashMap<String, String>();
		            nameMap.put("#us", "username");
		            nameMap.put("#pas", "password");
	
		            HashMap<String, Object> valueMap = new HashMap<String, Object>();
		            valueMap.put(":usr",userid);
		            valueMap.put(":pass",pwd);
	            
	            	QuerySpec querySpec = new QuerySpec()
	                .withKeyConditionExpression("#us = :usr and #pas = :pass" )
	                 .withNameMap(nameMap).withValueMap(valueMap);	            

		            ItemCollection<QueryOutcome> items = null;
		            Iterator<Item> iterator = null;
		            Item item = null;
	          
	                items = table.query(querySpec);
	                iterator = items.iterator();
	                while (iterator.hasNext()) {
	                    item = iterator.next();
	                    System.out.println(item.toString());
	                }
	            }
	            catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException, which means your request made it "
	                    + "to AWS, but was rejected with an error response for some reason.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
		        } catch (AmazonClientException ace) {
		            System.out.println("Caught an AmazonClientException, which means the client encountered "
		                    + "a serious internal problem while trying to communicate with AWS, "
		                    + "such as not being able to access the network.");
		            System.out.println("Error Message: " + ace.getMessage());
		        }
/*		String newPwd=pwd;
		
		Customer c = new Customer();
		query="select * from customer where userid='"+userid+"' and Password='"+newPwd+"'";
		//String sql = "select * from user where User_id=? and Password=?;";

		connection = ConnectionSetter.getConnection();
		ps = connection.prepareStatement(query);
	//	ps.setString(1, userid);
	//	ps.setString(2, pwd);
		rs = ps.executeQuery();

		if (rs.next()) {
						
			c.setNric(rs.getString("nric"));
			c.setFirstName(rs.getString("firstname"));
			c.setLastName(rs.getString("lastname"));
			c.setDepartment(rs.getString("department"));
			c.setUniversity(rs.getString("university"));
			c.setQuestion(rs.getString("question"));
			c.setAnswer(rs.getString("answer"));
			c.setPassword(rs.getString("Password"));
			c.setUserid(rs.getString("userid"));
			c.setEmail(rs.getString("email"));
			c.setPhone(Long.parseLong(rs.getString("phone")));
		
		}
		// connection.commit();
		close();*/

		return null;
	}
	private static Object scan(QuerySpec scanSpec) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertCustomer(Customer c) throws Exception {
		init();

        try {
            String tableName = "customer";

            // Create table if it does not exist yet
            if (Tables.doesTableExist(dynamoDB, tableName)) {
                System.out.println("Table " + tableName + " is already ACTIVE");
            } else {
            
            	System.out.println("Table nahi hega");
            }
        

            // Add an item
            Map<String, AttributeValue> item = newItem(c);
            PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
            PutItemResult putItemResult = dynamoDB.putItem(putItemRequest);
            System.out.println("Result: " + putItemResult);



        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to AWS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with AWS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
		/*connection = ConnectionSetter.getConnection();
		query = "insert into customer(firstname,lastname,department,university,userid,Password,question,answer,email,phone) "
				+ "values ('"+c.getFirstName()+"','"+c.getLastName()+"','"+c.getDepartment()+"','"+c.getUniversity()+"','"+c.getUserid()+"'"
						+ ",'"+c.getPassword()+"','"+c.getQuestion()+"','"+c.getAnswer()+"','"+c.getEmail()+"','"+c.getPhone()+"')";

		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(query);
			ps.executeUpdate();
			
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing insert: " + query);

			connection.commit();
		
		} catch (SQLException e) {
			error = "Error in Insert Customer :" + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
		} finally {
			close();
		}*/
	}
	private static Map<String, AttributeValue> newItem(Customer c) {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("username", new AttributeValue(c.getUserid()));
        item.put("password", new AttributeValue(c.getPassword()));
        item.put("email", new AttributeValue(c.getEmail()));
        item.put("phone", new AttributeValue(String.valueOf(c.getPhone())));
        item.put("department", new AttributeValue(c.getDepartment()));
        item.put("university", new AttributeValue(c.getUniversity()));
        item.put("name", new AttributeValue(c.getFirstName()+" "+c.getLastName()));
        item.put("question", new AttributeValue(c.getQuestion()));
        item.put("answer", new AttributeValue(c.getAnswer()));
      //  item.put("fans", new AttributeValue().withSS(i));
        return item;
    }
	private static Map<String, AttributeValue> newItemTeamMate(TeamMate c) {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
     
        item.put("department", new AttributeValue(c.getDepartment()));
        item.put("university", new AttributeValue(c.getUniversity()));
        item.put("name", new AttributeValue(c.getFirstName()+" "+c.getLastName()));
        item.put("course", new AttributeValue(c.getCourse()));
      
      //  item.put("fans", new AttributeValue().withSS(i));
        return item;
    }
	
	public void insertTeamMate(TeamMate tm) {
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
            String tableName = "student";

            // Create table if it does not exist yet
            if (Tables.doesTableExist(dynamoDB, tableName)) {
                System.out.println("Table " + tableName + " is already ACTIVE");
            } else {
            
            	System.out.println("Table nahi hega");
            }
        

            // Add an item
            Map<String, AttributeValue> item = newItemTeamMate(tm);
            PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
            PutItemResult putItemResult = dynamoDB.putItem(putItemRequest);
            System.out.println("Result: " + putItemResult);



        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to AWS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with AWS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
		
		/*connection = ConnectionSetter.getConnection();
		query = "insert into student(firstname,lastname,course,department,university) values ('"+tm.getFirstName()+"',"
				+ "'"+tm.getLastName()+"','"+tm.getCourse()+"','"+tm.getDepartment()+"','"+tm.getUniversity()+"')";

		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(query);
			ps.executeUpdate();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing insert: " + query);
			System.out.println("In student  --------- "+tm);
			connection.commit();

		} catch (SQLException e) {
			error = "Error in Insert Customer :" + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
		} finally {
			close();
		}*/
	}
	
	public void insertRating(Rating rating,String fName,String lName,String uni,String rater) {
		
		StringBuffer sb = new StringBuffer();
		ArrayList<String> tags= rating.getTag();
		Iterator<String> itr = tags.iterator();
		int i=0;
		while (itr.hasNext()) {
			if(i==0){
			sb.append(itr.next());
			i++;
			}
			else{
				sb.append("--");
				sb.append(itr.next());
			}
			System.out.println(sb);
		}
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
            String tableName = "rating";

            // Create table if it does not exist yet
            if (Tables.doesTableExist(dynamoDB, tableName)) {
                System.out.println("Table " + tableName + " is already ACTIVE");
            } else {
            
            	System.out.println("Table nahi hega");
            }
        

            // Add an item
            Map<String, AttributeValue> item = newItemRating( rating, fName, lName, uni, rater,sb);
            PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
            PutItemResult putItemResult = dynamoDB.putItem(putItemRequest);
            System.out.println("Result: " + putItemResult);



        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to AWS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with AWS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
		
		
		/*connection = ConnectionSetter.getConnection();
		query = "insert into rating(coursecode,helpful,knowledge,leader,participation,tag,comment,averageRating,firstname,lastname,university,rater) values "
		+ "('"+rating.getCourseCode()+"','"+rating.getHelpfulness()+"','"+rating.getKnowledgeable()+"','"+rating.getLeadership()+"',"
		+ "'"+rating.getTeamParticipation()+"','"+sb+"','"+rating.getComment()+"','"+rating.getAverage()+"' ,'"+fName+"','"+lName+"'"
				+ ",'"+uni+"','"+rater+"')";

		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(query);
			ps.executeUpdate();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing insert: " + query);
			System.out.println("In student  --------- "+rating);
			connection.commit();

		} catch (SQLException e) {
			error = "Error in Insert Customer :" + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
		} finally {
			close();
		}*/
	}
	private static Map<String, AttributeValue> newItemRating(Rating rating,String fName,String lName,String uni,String rater,StringBuffer tags) {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
     
        item.put("tag", new AttributeValue(tags.toString()));
        item.put("university", new AttributeValue(uni));
        item.put("name", new AttributeValue(fName+" "+lName));
        item.put("rater", new AttributeValue(rater));
        item.put("coursecode", new AttributeValue(rating.getCourseCode()));
        item.put("helpful", new AttributeValue(rating.getHelpfulness()));
        item.put("knowledge", new AttributeValue(rating.getKnowledgeable()));
        item.put("leader", new AttributeValue(rating.getLeadership()));
        item.put("participation", new AttributeValue(rating.getTeamParticipation()));
        item.put("comment", new AttributeValue(rating.getComment()));
        item.put("averageRating", new AttributeValue(rating.getAverage()));
       
      
      //  item.put("fans", new AttributeValue().withSS(i));
        return item;
    }

	
	
	public Customer findCustomer(String UserName, String password){
		 Customer c=new Customer();
		 try {
				init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        try {
		            String tableName = "customer";
		           
		            // Create table if it does not exist yet
		            if (Tables.doesTableExist(dynamoDB, tableName)) {
		                System.out.println("Table " + tableName + " is already ACTIVE");
		            } else {
		                
		            	System.out.println("Table nahi hega");
		            }

		            DynamoDB dynam = new DynamoDB(dynamoDB);	            
		            Table table =  dynam.getTable(tableName);
		            HashMap<String, String> nameMap = new HashMap<String, String>();
		            nameMap.put("#us", "username");
		           
	
		            HashMap<String, Object> valueMap = new HashMap<String, Object>();
		            valueMap.put(":usr",UserName);
		            
	            
	            	QuerySpec querySpec = new QuerySpec()
	                .withKeyConditionExpression("#us = :usr" )
	                 .withNameMap(nameMap).withValueMap(valueMap);	            

		            ItemCollection<QueryOutcome> items = null;
		            Iterator<Item> iterator = null;
		            Item item = null;
	          
	                items = table.query(querySpec);
	                iterator = items.iterator();
	                while (iterator.hasNext()) {
	                    item = iterator.next();
	                    String name=item.getString("name");
	                    c.setFirstName(name);
	                    System.out.println(item.toString());
	                }
	            }
	            catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException, which means your request made it "
	                    + "to AWS, but was rejected with an error response for some reason.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
		        } catch (AmazonClientException ace) {
		            System.out.println("Caught an AmazonClientException, which means the client encountered "
		                    + "a serious internal problem while trying to communicate with AWS, "
		                    + "such as not being able to access the network.");
		            System.out.println("Error Message: " + ace.getMessage());
		        }
		/*query="select * from customer where firstname='"+firstName+"' and lastname='"+lastName+"'";
		connection=ConnectionSetter.getConnection();
		
		Customer c=new Customer();
		try {

			statement=connection.createStatement();
			rs = statement.executeQuery(query);
			
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing select: " + query);

			while (rs.next()) {
				c.setNric(rs.getString("nric"));
				c.setFirstName(rs.getString("firstname"));
				c.setLastName(rs.getString("lastname"));
				c.setDepartment(rs.getString("department"));
				c.setUniversity(rs.getString("university"));
				c.setQuestion(rs.getString("question"));
				c.setAnswer(rs.getString("answer"));
				c.setPassword(rs.getString("Password"));
				c.setUserid(rs.getString("userid"));
				c.setEmail(rs.getString("email"));
				c.setPhone(Long.parseLong(rs.getString("phone")));
			}

		}catch (SQLException e) {
			error="Error in selecting customer :" + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
		} finally {
			close();
		}
*/
		return c;
	}
	
	
	public TeamMate findStudent(String firstName,String lastName,String uniName){
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TeamMate c=new TeamMate();
        try {
	            String tableName = "student";

	            // Create table if it does not exist yet
	            if (Tables.doesTableExist(dynamoDB, tableName)) {
	                System.out.println("Table " + tableName + " is already ACTIVE");
	            } else {
	                
	            	System.out.println("Table nahi hega");
	            }

	            DynamoDB dynam = new DynamoDB(dynamoDB);	            
	            Table table =  dynam.getTable(tableName);
	            HashMap<String, String> nameMap = new HashMap<String, String>();
	            nameMap.put("#us", "name");
	            nameMap.put("#uni", "university");
	           

	            HashMap<String, Object> valueMap = new HashMap<String, Object>();
	            valueMap.put(":usr",firstName+" "+lastName);
	            valueMap.put(":usr1",uniName);
	            
	            QuerySpec querySpec = new QuerySpec()
                .withKeyConditionExpression("#us = :usr and #uni = :usr1" )
                 .withNameMap(nameMap).withValueMap(valueMap);	            

	            ItemCollection<QueryOutcome> items = null;
	            Iterator<Item> iterator = null;
	            Item item = null;
          
                items = table.query(querySpec);
                iterator = items.iterator();
                while (iterator.hasNext()) {
                    item = iterator.next();
                    String name=item.getString("name");
                    String uni=item.getString("university");
                    String course=item.getString("course");
                    String dept=item.getString("department");
                    String [] nameArray = name.split(" ");
                    c.setFirstName(nameArray[0]);
                    c.setLastName(nameArray[1]);
                    c.setUniversity(uni);
                    c.setCourse(course);
                    c.setDepartment(dept);
                    System.out.println(item.toString());
                }
            }
            catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to AWS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException, which means the client encountered "
	                    + "a serious internal problem while trying to communicate with AWS, "
	                    + "such as not being able to access the network.");
	            System.out.println("Error Message: " + ace.getMessage());
	        }
/*		query="select * from student where firstname='"+firstName+"' and lastname='"+lastName+"' and university='"+uniName+"'";
		connection=ConnectionSetter.getConnection();
		
		TeamMate c=new TeamMate();
		try {

			statement=connection.createStatement();
			rs = statement.executeQuery(query);
			
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing select: " + query);

			while (rs.next()) {
				c.setFirstName(rs.getString("firstname"));
				c.setLastName(rs.getString("lastname"));
				c.setUniversity(rs.getString("university"));
				c.setId(Integer.parseInt(rs.getString("id")));
				c.setCourse(rs.getString("course"));
				c.setDepartment(rs.getString("department"));
			}

		}catch (SQLException e) {
			error="Error in selecting customer :" + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
		} finally {
			close();
		}*/

		return c;
	}
	
	
	public ArrayList<String> findStudentAjax(String firstName,String lastName){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> newlist = new ArrayList<String>();
		 try {
				init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        try {
		            String tableName = "student";
	
		            // Create table if it does not exist yet
		            if (Tables.doesTableExist(dynamoDB, tableName)) {
		                System.out.println("Table " + tableName + " is already ACTIVE");
		            } else {
		                
		            	System.out.println("Table nahi hega");
		            }

		            DynamoDB dynam = new DynamoDB(dynamoDB);	            
		            Table table =  dynam.getTable(tableName);
		            HashMap<String, String> nameMap = new HashMap<String, String>();
		            nameMap.put("#us", "name");
		            nameMap.put("#uni", "university");
		           
	
		            
		            ScanSpec scanSpec = new ScanSpec()
		            .withProjectionExpression("#us,#uni").withNameMap(nameMap);

		            ItemCollection<ScanOutcome> items =table.scan(scanSpec);

		            Iterator<Item> iter = items.iterator();
		            while (iter.hasNext()) {
		                Item item = iter.next();
		                String name=item.getString("name");
		                String uni=item.getString("university");
		                String person = name+"---"+uni;
		                list.add(person);
		                System.out.println(item.toString());
		            }
	            }
	            catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException, which means your request made it "
	                    + "to AWS, but was rejected with an error response for some reason.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
		        } catch (AmazonClientException ace) {
		            System.out.println("Caught an AmazonClientException, which means the client encountered "
		                    + "a serious internal problem while trying to communicate with AWS, "
		                    + "such as not being able to access the network.");
		            System.out.println("Error Message: " + ace.getMessage());
		        }
		/*ArrayList<String> list = new ArrayList<String>();
		query="select * from student where firstname like '"+firstName+"%"+"'";
		connection=ConnectionSetter.getConnection();
		
		TeamMate c=new TeamMate();
		try {

			statement=connection.createStatement();
			rs = statement.executeQuery(query);
			
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing select: " + query);

			while (rs.next()) {
				
				String fName=rs.getString("firstname");
				String lName=rs.getString("lastname");
				String uni=rs.getString("university");
				String name =fName+" "+lName+"---"+uni;
				list.add(name);
			}

		}catch (SQLException e) {
			error="Error in selecting customer :" + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
		} finally {
			close();
		}*/
	        for (String swearWord : list) {
	            Pattern pat = Pattern.compile(firstName, Pattern.CASE_INSENSITIVE);
	            Matcher mat = pat.matcher(swearWord);
	          
	            while (mat.find()) {
	            	
	            	newlist.add(swearWord);
	              }	           
	            
	           }

		return newlist;
	}
	public TeamMate findRater(String firstName,String lastName,String uniName,String rater){
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TeamMate c=new TeamMate();
        try {
	            String tableName = "rating";

	            // Create table if it does not exist yet
	            if (Tables.doesTableExist(dynamoDB, tableName)) {
	                System.out.println("Table " + tableName + " is already ACTIVE");
	            } else {
	                
	            	System.out.println("Table nahi hega");
	            }

	            DynamoDB dynam = new DynamoDB(dynamoDB);	            
	            Table table =  dynam.getTable(tableName);
	            HashMap<String, String> nameMap = new HashMap<String, String>();
	            nameMap.put("#us", "name");
	            nameMap.put("#uni", "university");
	           

	            HashMap<String, Object> valueMap = new HashMap<String, Object>();
	            valueMap.put(":usr",firstName+" "+lastName);
	            valueMap.put(":usr1",uniName);
	            
	            QuerySpec querySpec = new QuerySpec()
                .withKeyConditionExpression("#us = :usr and #uni = :usr1" )
                 .withNameMap(nameMap).withValueMap(valueMap);	            

	            ItemCollection<QueryOutcome> items = null;
	            Iterator<Item> iterator = null;
	            Item item = null;
          
                items = table.query(querySpec);
                iterator = items.iterator();
                while (iterator.hasNext()) {
                    item = iterator.next();
                    String name=item.getString("rater");
                    if(rater.equalsIgnoreCase(name))
                    c.setCourse(name);
                    System.out.println(item.toString());
                }
            }
            catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to AWS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException, which means the client encountered "
	                    + "a serious internal problem while trying to communicate with AWS, "
	                    + "such as not being able to access the network.");
	            System.out.println("Error Message: " + ace.getMessage());
	        }
		/*query="select * from rating where firstname='"+firstName+"' and lastname='"+lastName+"' and university='"+uniName+"'"
				+ "and rater='"+rater+"'";
		connection=ConnectionSetter.getConnection();
		
		TeamMate c=new TeamMate();
		try {

			statement=connection.createStatement();
			rs = statement.executeQuery(query);
			
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing select: " + query);

			while (rs.next()) {
				
				c.setCourse("rater"); // used as a flag
				
			}

		}catch (SQLException e) {
			error="Error in selecting customer :" + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
		} finally {
			close();
		}*/

		return c;
	}
	
	
	public void updatePassword(String nric, String pwd) {
		String newPwd=pwd;
		
		connection=ConnectionSetter.getConnection();
		query="update customer SET Password=? where nric=?";
		
		try {
			connection.setAutoCommit(false);
			
			ps=connection.prepareStatement(query);
			
			ps.setString(1, newPwd);
			ps.setString(2, nric);
			
			ps.executeUpdate();
			Logger.getLogger(getClass().getName()).log(Level.INFO,"Executing update: " + query);
			
			connection.commit();
			
		} catch (SQLException e) {
			error="Error in Update Bank Account Password :" + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
		}finally{
			close();
		}
	}
	public Customer getCustomersByUserId(String userid)
			throws SQLException {

		Customer c = new Customer();
		query="select * from customer where userid='"+userid+"'";
		//String sql = "select * from user where User_id=? and Password=?;";

		connection = ConnectionSetter.getConnection();
		ps = connection.prepareStatement(query);
	//	ps.setString(1, userid);
	//	ps.setString(2, pwd);
		rs = ps.executeQuery();

		if (rs.next()) {
			c.setNric(rs.getString("nric"));
			c.setFirstName(rs.getString("firstname"));
			c.setLastName(rs.getString("lastname"));
			c.setDepartment(rs.getString("department"));
			c.setUniversity(rs.getString("university"));
			c.setQuestion(rs.getString("question"));
			c.setAnswer(rs.getString("answer"));
			c.setPassword(rs.getString("Password"));
			c.setUserid(rs.getString("userid"));
			c.setEmail(rs.getString("email"));
			c.setPhone(Long.parseLong(rs.getString("phone")));
		}
		// connection.commit();
		close();

		return c;
	}
	
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}

		} catch (Exception e) {

		}
	}
	
}
