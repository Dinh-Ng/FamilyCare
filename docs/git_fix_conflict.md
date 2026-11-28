## Cách fix khi có conflict code khi tạo PR

1. **Quay về branch chính (`main`/`master`):**

   ```bash
   git checkout master
   ```

2. **Lấy code về:**

   ```bash
   git fetch --all
   git pull origin master
   ```

3. **Quay về branch của bản thân:**

   ```bash
   git checkout dinhnq
   ```

4. **Gộp code từ branch chính về branch của bản thân:**

   ```bash
   git merge master
   ```

5. **Xử lý conflict:**
   - Khi conflict hiện ra, mở **VS Code** để xử lý.
   - Chọn giữ lại phần code đúng (`Accept Current Change` / `Accept Incoming Change` / `Accept Both Changes`).
   - Xóa các ký hiệu đánh dấu nếu có (`<<<<<<<`, `=======`, `>>>>>>>`).

6. **Lưu và commit phần code đã sửa conflict:**

   ```bash
   git add .
   git commit -m "fix conflict with master"
   ```

7. **Đẩy lại code lên branch của bản thân:**

   ```bash
   git push origin dinhnq
   ```

8. **Hoàn tất:**
   - PR sẽ tự động cập nhật, báo cáo lại với trưởng nhóm.
