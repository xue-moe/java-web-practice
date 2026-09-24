// 三个练习使用独立的 localStorage 键，数据保存在当前浏览器中。
const STORAGE_KEYS = {
  employees: 'java-web-practice.employees',
  books: 'java-web-practice.books',
  grades: 'java-web-practice.grades'
};

// 首次打开时展示虚构的样例数据，方便直接体验各项操作。
const SAMPLE_DATA = {
  employees: [
    { id: 'e1', name: '林晓', department: '技术部', role: '后端开发', date: '2024-03-18', salary: 9200 },
    { id: 'e2', name: '周宁', department: '设计部', role: '产品设计', date: '2023-11-06', salary: 8600 },
    { id: 'e3', name: '陈可', department: '运营部', role: '内容运营', date: '2025-01-13', salary: 7200 }
  ],
  books: [
    { id: 'b1', title: 'Head First Java', author: 'Kathy Sierra', category: '计算机', total: 4, available: 3 },
    { id: 'b2', title: '小王子', author: '安托万·德·圣-埃克苏佩里', category: '文学', total: 5, available: 5 },
    { id: 'b3', title: '编码：隐匿在计算机软硬件背后的语言', author: 'Charles Petzold', category: '计算机', total: 2, available: 1 }
  ],
  grades: [
    { id: 'g1', student: '许安', course: '程序设计基础', score: 88 },
    { id: 'g2', student: '叶青', course: '数据库原理', score: 76 },
    { id: 'g3', student: '许安', course: '数据库原理', score: 93 },
    { id: 'g4', student: '叶青', course: '程序设计基础', score: 59 }
  ]
};

// localStorage 返回字符串，因此读取时统一解析并检查数据格式。
function loadRecords(key, sampleRecords) {
  try {
    const saved = localStorage.getItem(key);
    if (saved !== null) {
      const records = JSON.parse(saved);
      if (Array.isArray(records)) return records;
    }
  } catch (error) {
    // 浏览器存储被清空或内容损坏时，继续使用下方的样例数据。
  }

  const initialRecords = sampleRecords.map(record => ({ ...record }));
  saveRecords(key, initialRecords);
  return initialRecords;
}

function saveRecords(key, records) {
  localStorage.setItem(key, JSON.stringify(records));
}

function createId(prefix) {
  // randomUUID 在本地文件等环境下可能不可用，所以保留一个简单回退方案。
  const uniquePart = window.crypto && crypto.randomUUID
    ? crypto.randomUUID()
    : Date.now() + '-' + Math.random().toString(16).slice(2);
  return prefix + '-' + uniquePart;
}

function addCell(row, value) {
  const cell = document.createElement('td');
  // 使用 textContent 显示输入值，避免把用户输入解析为 HTML。
  cell.textContent = value;
  row.append(cell);
  return cell;
}

function addAction(cell, label, style, handler, disabled = false) {
  const button = document.createElement('button');
  button.type = 'button';
  button.className = 'btn small ' + style;
  button.textContent = label;
  button.disabled = disabled;
  button.addEventListener('click', handler);
  cell.append(button);
}

let employees = loadRecords(STORAGE_KEYS.employees, SAMPLE_DATA.employees);
let books = loadRecords(STORAGE_KEYS.books, SAMPLE_DATA.books);
let grades = loadRecords(STORAGE_KEYS.grades, SAMPLE_DATA.grades);

// 项目 01：员工档案
const employeeForm = document.querySelector('#employee-form');

function resetEmployeeForm() {
  employeeForm.reset();
  document.querySelector('#employee-id').value = '';
  document.querySelector('#employee-cancel').hidden = true;
}

function renderEmployees() {
  const searchText = document.querySelector('#employee-search').value.trim().toLowerCase();
  const matches = employees.filter(employee => {
    const searchableFields = [employee.name, employee.department, employee.role];
    return searchableFields.some(value => value.toLowerCase().includes(searchText));
  });
  const tableBody = document.querySelector('#employee-rows');
  tableBody.replaceChildren();

  // 汇总值使用全部员工计算，搜索只影响表格中显示的行。
  document.querySelector('#employee-count').textContent = employees.length;
  document.querySelector('#employee-departments').textContent = new Set(
    employees.map(employee => employee.department)
  ).size;
  document.querySelector('#employee-empty').hidden = matches.length > 0;

  matches.forEach(employee => {
    const row = document.createElement('tr');
    addCell(row, employee.name);
    addCell(row, employee.department);
    addCell(row, employee.role);
    addCell(row, employee.date);
    addCell(row, '¥' + Number(employee.salary).toLocaleString());

    const actions = document.createElement('td');
    addAction(actions, '编辑', 'alt', () => {
      document.querySelector('#employee-id').value = employee.id;
      document.querySelector('#employee-name').value = employee.name;
      document.querySelector('#employee-department').value = employee.department;
      document.querySelector('#employee-role').value = employee.role;
      document.querySelector('#employee-date').value = employee.date;
      document.querySelector('#employee-salary').value = employee.salary;
      document.querySelector('#employee-cancel').hidden = false;
      document.querySelector('#employee-name').focus();
    });
    addAction(actions, '删除', 'danger', () => {
      employees = employees.filter(record => record.id !== employee.id);
      saveRecords(STORAGE_KEYS.employees, employees);
      renderEmployees();
    });
    row.append(actions);
    tableBody.append(row);
  });
}

employeeForm.addEventListener('submit', event => {
  event.preventDefault();
  const editingId = document.querySelector('#employee-id').value;
  const employee = {
    id: editingId || createId('employee'),
    name: document.querySelector('#employee-name').value.trim(),
    department: document.querySelector('#employee-department').value,
    role: document.querySelector('#employee-role').value.trim(),
    date: document.querySelector('#employee-date').value,
    salary: Number(document.querySelector('#employee-salary').value)
  };

  if (!employee.name || !employee.role || !employee.date || employee.salary < 0) return;
  employees = editingId
    ? employees.map(record => record.id === editingId ? employee : record)
    : [employee, ...employees];
  saveRecords(STORAGE_KEYS.employees, employees);
  resetEmployeeForm();
  renderEmployees();
});

document.querySelector('#employee-cancel').addEventListener('click', resetEmployeeForm);
document.querySelector('#employee-search').addEventListener('input', renderEmployees);

// 项目 02：图书库存
const bookForm = document.querySelector('#book-form');

function resetBookForm() {
  bookForm.reset();
  document.querySelector('#book-id').value = '';
  document.querySelector('#book-total').value = '1';
  document.querySelector('#book-cancel').hidden = true;
}

function changeAvailableCopies(bookId, amount) {
  books = books.map(book => {
    if (book.id !== bookId) return book;
    // 可借册数必须保持在 0 到馆藏总数之间。
    const nextAvailable = Math.max(0, Math.min(book.total, book.available + amount));
    return { ...book, available: nextAvailable };
  });
  saveRecords(STORAGE_KEYS.books, books);
  renderBooks();
}

function renderBooks() {
  const searchText = document.querySelector('#book-search').value.trim().toLowerCase();
  const matches = books.filter(book => {
    const searchableFields = [book.title, book.author, book.category];
    return searchableFields.some(value => value.toLowerCase().includes(searchText));
  });
  const tableBody = document.querySelector('#book-rows');
  tableBody.replaceChildren();
  document.querySelector('#book-count').textContent = books.length;
  document.querySelector('#book-available-total').textContent = books.reduce(
    (total, book) => total + book.available,
    0
  );
  document.querySelector('#book-empty').hidden = matches.length > 0;

  matches.forEach(book => {
    const row = document.createElement('tr');
    addCell(row, book.title);
    addCell(row, book.author);
    addCell(row, book.category);
    addCell(row, book.total);
    addCell(row, book.available);

    const actions = document.createElement('td');
    addAction(actions, '借出', 'alt', () => changeAvailableCopies(book.id, -1), book.available === 0);
    addAction(actions, '归还', 'alt', () => changeAvailableCopies(book.id, 1), book.available === book.total);
    addAction(actions, '编辑', 'alt', () => {
      document.querySelector('#book-id').value = book.id;
      document.querySelector('#book-title').value = book.title;
      document.querySelector('#book-author').value = book.author;
      document.querySelector('#book-category').value = book.category;
      document.querySelector('#book-total').value = book.total;
      document.querySelector('#book-cancel').hidden = false;
    });
    // 有书尚未归还时不能删除整条书目，以免库存记录失真。
    addAction(actions, '删除', 'danger', () => {
      books = books.filter(record => record.id !== book.id);
      saveRecords(STORAGE_KEYS.books, books);
      renderBooks();
    }, book.available !== book.total);
    row.append(actions);
    tableBody.append(row);
  });
}

bookForm.addEventListener('submit', event => {
  event.preventDefault();
  const editingId = document.querySelector('#book-id').value;
  const totalCopies = Number(document.querySelector('#book-total').value);
  const oldBook = books.find(book => book.id === editingId);
  const borrowedCopies = oldBook ? oldBook.total - oldBook.available : 0;

  // 编辑图书时，馆藏总数不能低于当前已经借出的册数。
  if (totalCopies < 1 || totalCopies < borrowedCopies) {
    alert('馆藏册数不能小于当前已借出的数量。');
    return;
  }

  const book = {
    id: editingId || createId('book'),
    title: document.querySelector('#book-title').value.trim(),
    author: document.querySelector('#book-author').value.trim(),
    category: document.querySelector('#book-category').value,
    total: totalCopies,
    available: totalCopies - borrowedCopies
  };
  if (!book.title || !book.author) return;

  books = editingId
    ? books.map(record => record.id === editingId ? book : record)
    : [book, ...books];
  saveRecords(STORAGE_KEYS.books, books);
  resetBookForm();
  renderBooks();
});

document.querySelector('#book-cancel').addEventListener('click', resetBookForm);
document.querySelector('#book-search').addEventListener('input', renderBooks);

// 项目 03：课程成绩
const gradeForm = document.querySelector('#grade-form');

function resetGradeForm() {
  gradeForm.reset();
  document.querySelector('#grade-id').value = '';
  document.querySelector('#grade-cancel').hidden = true;
}

function renderGrades() {
  const searchText = document.querySelector('#grade-search').value.trim().toLowerCase();
  const matches = grades.filter(grade =>
    grade.student.toLowerCase().includes(searchText) ||
    grade.course.toLowerCase().includes(searchText)
  );
  const tableBody = document.querySelector('#grade-rows');
  tableBody.replaceChildren();

  // 统计基于所有成绩记录，而不是当前搜索结果。
  const average = grades.length
    ? grades.reduce((total, grade) => total + grade.score, 0) / grades.length
    : 0;
  document.querySelector('#grade-count').textContent = grades.length;
  document.querySelector('#grade-average').textContent = average.toFixed(1);
  document.querySelector('#grade-pass').textContent = grades.filter(grade => grade.score >= 60).length;
  document.querySelector('#grade-empty').hidden = matches.length > 0;

  matches.forEach(grade => {
    const row = document.createElement('tr');
    addCell(row, grade.student);
    addCell(row, grade.course);
    addCell(row, grade.score);
    addCell(row, grade.score >= 60 ? '及格' : '待补考');

    const actions = document.createElement('td');
    addAction(actions, '编辑', 'alt', () => {
      document.querySelector('#grade-id').value = grade.id;
      document.querySelector('#grade-student').value = grade.student;
      document.querySelector('#grade-course').value = grade.course;
      document.querySelector('#grade-score').value = grade.score;
      document.querySelector('#grade-cancel').hidden = false;
    });
    addAction(actions, '删除', 'danger', () => {
      grades = grades.filter(record => record.id !== grade.id);
      saveRecords(STORAGE_KEYS.grades, grades);
      renderGrades();
    });
    row.append(actions);
    tableBody.append(row);
  });
}

gradeForm.addEventListener('submit', event => {
  event.preventDefault();
  const editingId = document.querySelector('#grade-id').value;
  const student = document.querySelector('#grade-student').value.trim();
  const score = Number(document.querySelector('#grade-score').value);
  if (!student || !Number.isFinite(score) || score < 0 || score > 100) return;

  const course = document.querySelector('#grade-course').value;
  const duplicate = grades.some(grade =>
    grade.id !== editingId && grade.student === student && grade.course === course
  );
  if (duplicate) {
    alert('这名学生已经有该课程的成绩记录。请编辑原记录。');
    return;
  }

  const grade = { id: editingId || createId('grade'), student, course, score };
  grades = editingId
    ? grades.map(record => record.id === editingId ? grade : record)
    : [grade, ...grades];
  saveRecords(STORAGE_KEYS.grades, grades);
  resetGradeForm();
  renderGrades();
});

document.querySelector('#grade-cancel').addEventListener('click', resetGradeForm);
document.querySelector('#grade-search').addEventListener('input', renderGrades);

// 标签导航同步地址栏片段，这样刷新后仍会回到当前练习。
function showPanel(name) {
  document.querySelectorAll('[data-panel]').forEach(panel => {
    panel.classList.toggle('active', panel.dataset.panel === name);
  });
  document.querySelectorAll('[data-tab]').forEach(tab => {
    tab.classList.toggle('active', tab.dataset.tab === name);
  });
}

document.querySelectorAll('[data-tab]').forEach(tab => {
  tab.addEventListener('click', () => showPanel(tab.dataset.tab));
});
window.addEventListener('hashchange', () => {
  showPanel(window.location.hash.slice(1) || 'employees');
});

const initialPanel = window.location.hash.slice(1) || 'employees';
showPanel(initialPanel);
renderEmployees();
renderBooks();
renderGrades();
