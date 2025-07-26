package com.saberoueslati.devbuddy.domain.model

import androidx.compose.ui.graphics.Color
import com.saberoueslati.devbuddy.R
import java.util.Date

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val status: TaskStatus,
    val tags: List<TaskTag>,
    val dueDate: Date,
    val estimateHours: Int,
    val hasCodeSnippet: Boolean = false
)

enum class Priority(val resourceId: Int, val color: Color) {
    CRITICAL(R.string.priority_critical, Color(0xFFEF4444)),
    HIGH(R.string.priority_high, Color(0xFFF97316)),
    MEDIUM(R.string.priority_medium, Color(0xFFF59E0B)),
    LOW(R.string.priority_low, Color(0xFF10B981))
}

enum class TaskStatus(val resourceId: Int, val color: Color) {
    TODO(R.string.status_todo, Color(0xFF6B7280)),
    IN_PROGRESS(R.string.status_in_progress, Color(0xFF3B82F6)),
    BLOCKED(R.string.status_blocked, Color(0xFFEF4444)),
    COMPLETED(R.string.status_completed, Color(0xFF10B981))
}

enum class TaskTag(val resourceId: Int, val color: Color, val backgroundColor: Color) {
    BUG_FIX(R.string.tag_bug_fix, Color(0xFFFCA5A5), Color(0xFF7F1D1D)),
    FEATURE(R.string.tag_feature, Color(0xFF93C5FD), Color(0xFF1E3A8A)),
    CODE_REVIEW(R.string.tag_code_review, Color(0xFFC4B5FD), Color(0xFF581C87)),
    DOCUMENTATION(R.string.tag_documentation, Color(0xFFFDE68A), Color(0xFF92400E)),
    PERFORMANCE(R.string.tag_performance, Color(0xFFA7F3D0), Color(0xFF14532D)),
    AUTHENTICATION(R.string.tag_authentication, Color(0xFFA5B4FC), Color(0xFF312E81)),
    UI_UX(R.string.tag_ui_ux, Color(0xFFF9A8D4), Color(0xFF831843)),
    PAYMENT(R.string.tag_payment, Color(0xFF67E8F9), Color(0xFF164E63)),
    API(R.string.tag_api, Color(0xFF5EEAD4), Color(0xFF134E4A)),
    DATABASE(R.string.tag_database, Color(0xFFFED7AA), Color(0xFF9A3412))
}