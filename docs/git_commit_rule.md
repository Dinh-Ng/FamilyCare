# Quy tắc viết Commit Message (Chuẩn Commitizen)

Để lịch sử Git rõ ràng, dễ theo dõi và thuận tiện cho việc review code, tất cả thành viên trong nhóm cần tuân thủ quy tắc viết commit message theo chuẩn **Commitizen (`cz`)**.

## 1. Cấu trúc chuẩn

```text
<type>(<scope>): <subject>
```

- **`<type>`** _(Bắt buộc)_: Loại thay đổi của commit.
- **`(<scope>)`** _(Tùy chọn)_: Phạm vi hoặc module bị ảnh hưởng (ví dụ: `auth`, `cart`, `api`, `ui`...).
- **`<subject>`** _(Bắt buộc)_: Mô tả ngắn gọn nội dung thay đổi.

---

## 2. Danh sách các `type` quy định

| Type           | Ý nghĩa                                                                                             | Ví dụ                                                      |
| :------------- | :-------------------------------------------------------------------------------------------------- | :--------------------------------------------------------- |
| **`feat`**     | Thêm tính năng mới                                                                                  | `feat(auth): thêm chức năng đăng nhập bằng Google`         |
| **`fix`**      | Sửa lỗi (bug)                                                                                       | `fix(cart): sửa lỗi tính sai tổng tiền khi áp mã giảm giá` |
| **`docs`**     | Viết hoặc cập nhật tài liệu (README, hướng dẫn, comment...)                                         | `docs: cập nhật hướng dẫn xử lý conflict khi tạo PR`       |
| **`style`**    | Chỉnh sửa không làm thay đổi logic hoạt động của code (format code, khoảng trắng, dấu chấm phẩy...) | `style: format lại code theo chuẩn prettier`               |
| **`refactor`** | Tái cấu trúc, dọn dẹp code nhưng không thêm tính năng mới hay sửa bug                               | `refactor(api): tối ưu hàm xử lý phân trang`               |
| **`chore`**    | Các tác vụ cấu hình project, cập nhật thư viện/package, build...                                    | `chore: cập nhật phiên bản thư viện axios`                 |

---

## 3. Lưu ý khi viết `<subject>`

- Viết ngắn gọn, rõ ràng, đi thẳng vào vấn đề (nên dưới 70 ký tự).
- Không viết hoa chữ cái đầu sau dấu `:` và không đặt dấu chấm `.` ở cuối câu.
- Bắt đầu bằng động từ chỉ hành động (ví dụ: `thêm`, `sửa`, `cập nhật`, `xóa`, `tối ưu`...).
- Mỗi commit chỉ nên giải quyết **một mục đích duy nhất** (không gộp vừa `feat` vừa `fix` nhiều chỗ vào chung 1 commit).

---

## 4. Ví dụ thực tế

**Nên viết:**

```bash
git commit -m "feat(user): thêm API lấy danh sách người dùng"
git commit -m "fix(login): sửa lỗi không hiển thị thông báo khi sai mật khẩu"
git commit -m "docs: bổ sung quy tắc viết commit message"
git commit -m "style(header): căn chỉnh lại khoảng cách và format code"
```

**Không nên viết:**

```bash
git commit -m "update code"
git commit -m "fix bug"
git commit -m "Sửa lại giao diện và thêm chức năng đăng ký."
```
