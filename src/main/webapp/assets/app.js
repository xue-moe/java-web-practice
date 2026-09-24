document.addEventListener('submit', function (event) {
    var form = event.target;
    if (form.matches('[data-confirm-delete]') && !window.confirm('确定删除这条员工档案吗？此操作无法撤销。')) {
        event.preventDefault();
    }
});
