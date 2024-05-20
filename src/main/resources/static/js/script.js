document.addEventListener('DOMContentLoaded', function() {
    var readerSelect = document.getElementById('readerSelect');
    if (readerSelect) {
        readerSelect.addEventListener('change', function() {
            var readerId = this.value;
            if (readerId) {
                fetch('/getBooksByReader/' + readerId)
                    .then(response => response.text())
                    .then(html => {
                    var booksContainer = document.getElementById('booksContainer');
                    booksContainer.innerHTML = html; // Insert the HTML fragment
                })
                    .catch(error => console.error('Error fetching books:', error));
            }
        });
    }
});
