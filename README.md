# Employee API – Spring Boot AWS Integration

This Spring Boot REST API allows basic **CRUD operations** (Create, Read, Update, Delete) on `Employee` data.  
It is part of the `aws_integration` project.

## Base URL
```
/api/emp
```

## Endpoints

### 1. Get All Employees
**GET** `/api/emp`  
📄 Returns a list of all employees.

---

### 2. Get Employee by ID
**GET** `/api/emp/{empID}`  
🔍 Returns the employee with the given ID.

---

### 3. Create New Employee
**POST** `/api/emp`  
📝 Creates a new employee.  
**Body:** JSON with employee details.

---

### 4. Update Employee
**PUT** `/api/emp/{empID}`  
✏️ Updates the employee with the given ID.  
**Body:** JSON with updated employee data.

---

### 5. Delete Employee
**DELETE** `/api/emp/{empID}`  
🗑 Deletes the employee with the given ID.

---

## Example JSON (for POST/PUT)

```json
{
  "id": "101",
  "name": "John Doe",
  "role": "Developer",
  "email": "john@example.com"
}
```

---

Let me know if you also want to add setup steps or service layer details!