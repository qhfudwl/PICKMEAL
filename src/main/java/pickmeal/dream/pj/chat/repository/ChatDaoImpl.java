package pickmeal.dream.pj.chat.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.chat.domain.Chat;

@Repository("chatDao")
public class ChatDaoImpl implements ChatDao {
	
	@Autowired
	private JdbcTemplate jt;

	@Override
	public void addChat(Chat chat) {
		String sql = "INSERT INTO Chat(writerId, commenterId, memberId)"
				+ " VALUES (?, ?, ?)";
		jt.update(sql, chat.getWriter().getId(), chat.getCommenter().getId(), chat.getMember().getId());
	}

	@Override
	public void updateChat(Chat chat) {
		String sql = "UPDATE Chat SET writerId=?, commenterId=?, memberId=?";
		jt.update(sql, chat.getWriter().getId(), chat.getCommenter().getId(), chat.getMember().getId());
	}

	@Override
	public void deleteChat() {
		String sql = "DELETE FROM Chat WHERE DATE_SUB(DATE(NOW()), INTERVAL 60 DAY) > DATE(regDate)";
		jt.update(sql);
	}

	@Override
	public boolean isChatById(long id) {
		String sql = "SELECT EXISTS (SELECT id FROM Chat WHERE id=?)";
		return jt.queryForObject(sql, Boolean.class, id);
	}

	@Override
	public boolean isChatByMemberId(long memberId) {
		String sql = "SELECT EXISTS (SELECT id FROM Chat WHERE memberId=?)";
		return jt.queryForObject(sql, Boolean.class, memberId);
	}

	@Override
	public boolean isChatByWriterIdAndCommenterId(Chat chat) {
		String sql = "SELECT EXISTS (SELECT id FROM Chat WHERE writerId=? AND commenterId=? AND memberId=?)";
		return jt.queryForObject(sql, Boolean.class, chat.getWriter().getId(), chat.getCommenter().getId(), chat.getMember().getId());
	}

	@Override
	public Chat findChatByWriterIdAndCommenterId(Chat chat) {
		String sql = "SELECT id, writerId, commenterId, memberId, regDate"
				+ " FROM Chat WHERE writerId=? AND commenterId=? AND memberId=?";
		return jt.queryForObject(sql, new ChatRowMapper(), chat.getWriter().getId(), chat.getCommenter().getId(), chat.getMember().getId());
	}

	@Override
	public List<Chat> findAllChatsByMemberId(long memberId) {
		String sql = "SELECT id, writerId, commenterId, memberId, regDate"
				+ " FROM Chat WHERE memberId=?";
		return jt.query(sql, new ChatRowMapper(), memberId);
	}

	@Override
	public List<Chat> findLimitedChatsByMemberId(long memberId, long startId, int limit) {
		String sql = "SELECT id, writerId, commenterId, memberId, regDate"
				+ " FROM Chat WHERE memberId=? AND id > ? LIMIT ?";
		return jt.query(sql, new ChatRowMapper(), memberId, memberId, startId, limit);
	}

}
