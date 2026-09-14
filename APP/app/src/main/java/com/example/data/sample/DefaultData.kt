package com.example.data.sample

/**
 * Default offline/seed fallback data used when the app is launched for the very first time
 * before the first successful fetch from the GitHub repository.
 *
 * These JSON structures are 100% identical to the remote GitHub files:
 * - data/courses.json
 * - data/notices.json
 * - data/faculty.json
 * - data/results.json
 * - data/banners.json
 * - data/gallery.json
 * - data/settings.json
 */
object DefaultData {

    val COURSES_JSON = """
    [
      {
        "id": "course_wbcs",
        "title": "WBCS (Exe) Complete Foundation",
        "category": "Civil Services",
        "description": "Comprehensive one-year flagship classroom program covering WBCS Prelims (GS + English) & Mains (Compulsory & Optional guidance) with expert mentors.",
        "duration": "12 Months",
        "mode": "Offline Classroom / Hybrid",
        "fee": "₹18,500 (Easy Installments Available)",
        "poster": "https://images.unsplash.com/photo-1523240795612-9a054b0db644?w=800&q=80",
        "admissionInfo": "New Weekend & Weekday batches starting this month. Walk-in to Memari campus or call 6297034796 for free demo class registration.",
        "highlights": [
          "Complete updated printed study material",
          "Weekly 100-mark OMR mock tests with ranking",
          "Dedicated current affairs monthly compendium",
          "Interview grooming by former civil servants"
        ]
      },
      {
        "id": "course_ssc",
        "title": "SSC CGL / CHSL / MTS Master Batch",
        "category": "Staff Selection Commission",
        "description": "Targeted coaching focusing on Quantitative Aptitude shortcut tricks, Reasoning mastery, English comprehension & General Awareness.",
        "duration": "8 Months",
        "mode": "Offline Classroom",
        "fee": "₹12,000",
        "poster": "https://images.unsplash.com/photo-1434030216411-0b793f4b4173?w=800&q=80",
        "admissionInfo": "Batches running morning 8:00 AM & evening 4:30 PM. Direct admission open.",
        "highlights": [
          "5000+ Chapter-wise practice question bank",
          "Speed math calculation workshops",
          "Full-length Computer Based Test (CBT) simulations",
          "Previous 10 years TCS exam questions solved"
        ]
      },
      {
        "id": "course_rrb",
        "title": "Railways RRB NTPC & Group D Special",
        "category": "Railways",
        "description": "Complete exam oriented preparation for Non-Technical Popular Categories (NTPC), Assistant Loco Pilot (ALP), and Group D recruitments.",
        "duration": "6 Months",
        "mode": "Offline / Hybrid",
        "fee": "₹9,500",
        "poster": "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?w=800&q=80",
        "admissionInfo": "Limited 40 seats per batch for personalized attention. Admission ongoing.",
        "highlights": [
          "Intensive General Science & Numerical physics",
          "Bi-lingual notes (Bengali & English)",
          "Regular doubt clearance desk"
        ]
      },
      {
        "id": "course_police",
        "title": "WB Police SI & Constable Mission Batch",
        "category": "Police Services",
        "description": "Rigorous coaching for West Bengal Police Sub-Inspector, Sergeant & Constable written exams along with Physical Measurement Test (PMT) guidance.",
        "duration": "6 Months",
        "mode": "Offline Classroom",
        "fee": "₹8,000",
        "poster": "https://images.unsplash.com/photo-1544717305-2782549b5136?w=800&q=80",
        "admissionInfo": "Special physical fitness training guidance on weekends. Enroll now.",
        "highlights": [
          "Special focus on Bengali language paper for SI Mains",
          "Weekly OMR practice tests",
          "General Knowledge revision drills"
        ]
      },
      {
        "id": "course_wbpsc",
        "title": "WBPSC Miscellaneous & Clerkship Target",
        "category": "State Govt",
        "description": "Dedicated batch targeting Public Service Commission Clerkship, Miscellaneous Services, Food SI, and other Group C/D government posts.",
        "duration": "6 Months",
        "mode": "Offline Classroom",
        "fee": "₹9,000",
        "poster": "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=800&q=80",
        "admissionInfo": "Weekend Saturday-Sunday batch available for working aspirants.",
        "highlights": [
          "Descriptive writing workshop for Stage 2",
          "Grammar fundamentals & translation exercises",
          "State specific GK & current affairs"
        ]
      }
    ]
    """.trimIndent()

    val NOTICES_JSON = """
    [
      {
        "id": "not_1",
        "title": "New WBCS 2026-27 Weekend Batch Commences Sunday",
        "date": "15 Sep 2026",
        "description": "Orientation seminar and first foundation class will be held at our Memari campus from 10:30 AM. Registered aspirants are requested to collect their study kit.",
        "documentUrl": "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
        "category": "Admission",
        "isImportant": true
      },
      {
        "id": "not_2",
        "title": "WBPSC Clerkship Stage 1 Mock Test Series Schedule",
        "date": "10 Sep 2026",
        "description": "Comprehensive 10-Mock test series timetable published. Tests will be held every Sunday at 11:00 AM with immediate OMR evaluation and video analysis.",
        "documentUrl": "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
        "category": "Exams",
        "isImportant": true
      },
      {
        "id": "not_3",
        "title": "Free Scholarship Cum Talent Search Exam (MM-SAT)",
        "date": "04 Sep 2026",
        "description": "Medha Mantra Scholarship Aptitude Test offers up to 100% tuition waiver for meritorious candidates. Download registration form and syllabus details.",
        "documentUrl": "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
        "category": "Scholarship",
        "isImportant": false
      },
      {
        "id": "not_4",
        "title": "Current Affairs Monthly Capsule - August Edition Released",
        "date": "01 Sep 2026",
        "description": "Download our curated bilingual Current Affairs bulletin covering national, international, sports, awards, and West Bengal state initiatives.",
        "documentUrl": "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
        "category": "Study Material",
        "isImportant": false
      }
    ]
    """.trimIndent()

    val FACULTY_JSON = """
    [
      {
        "id": "fac_1",
        "name": "Prof. Anirban Banerjee",
        "subject": "General Studies & Indian Polity",
        "qualification": "M.A. (Pol Science), M.Phil, NET / SET Qualified",
        "experience": "11+ Years Teaching WBCS & UPSC Aspirants",
        "photo": "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=500&q=80",
        "bio": "Expert in Constitutional amendments, Article memorization techniques, and dynamic current polity analysis for civil services."
      },
      {
        "id": "fac_2",
        "name": "Dr. Sourav Mukherjee",
        "subject": "History & Indian National Movement",
        "qualification": "Ph.D. in Modern History, Former College Lecturer",
        "experience": "14+ Years in Competitive Exam Coaching",
        "photo": "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=500&q=80",
        "bio": "Known for chronological timeline storytelling methods and WBCS Mains GS Paper III question prediction."
      },
      {
        "id": "fac_3",
        "name": "Tanmoy Sen (Quant Expert)",
        "subject": "Quantitative Aptitude & Advanced Math",
        "qualification": "B.Tech (JU), SSC CGL 99.4 Percentiler in Quant",
        "experience": "9+ Years Training SSC, Railway & Bank Candidates",
        "photo": "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=500&q=80",
        "bio": "Pioneer of Vedic math shortcuts, mental calculation tricks, and data interpretation hacks."
      },
      {
        "id": "fac_4",
        "name": "Soma Ghosh",
        "subject": "English Language & Verbal Reasoning",
        "qualification": "M.A. in English Literature (CU), B.Ed",
        "experience": "8+ Years in Competitive English & Grammar",
        "photo": "https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?w=500&q=80",
        "bio": "Specializes in error detection, vocabulary roots, cloze test strategies, and descriptive essay composition."
      },
      {
        "id": "fac_5",
        "name": "Debabrata Roy",
        "subject": "General Science & Environment",
        "qualification": "M.Sc. (Physics), B.Sc. (Hons)",
        "experience": "10+ Years Mentoring Railways & PSC Students",
        "photo": "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=500&q=80",
        "bio": "Master of practical science concepts, NCERT fundamentals, and recent technological developments."
      }
    ]
    """.trimIndent()

    val RESULTS_JSON = """
    [
      {
        "id": "res_1",
        "studentName": "Subhajit Roy",
        "exam": "WBCS (Exe) Group A",
        "rankResult": "Rank 14 (Executive Cadre)",
        "year": "2025",
        "photo": "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?w=500&q=80"
      },
      {
        "id": "res_2",
        "studentName": "Priyanka Mondal",
        "exam": "WB Police Sub-Inspector",
        "rankResult": "Selected (UR Merit List 08)",
        "year": "2025",
        "photo": "https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=500&q=80"
      },
      {
        "id": "res_3",
        "studentName": "Rupam Chakraborty",
        "exam": "SSC CGL (Central Excise Inspector)",
        "rankResult": "All India Rank 342",
        "year": "2024",
        "photo": "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?w=500&q=80"
      },
      {
        "id": "res_4",
        "studentName": "Ananya Samanta",
        "exam": "WBPSC Clerkship Examination",
        "rankResult": "District Rank 03 (Purba Bardhaman)",
        "year": "2024",
        "photo": "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=500&q=80"
      },
      {
        "id": "res_5",
        "studentName": "Soumen Das",
        "exam": "Railways RRB NTPC (Station Master)",
        "rankResult": "Selected in Eastern Railway",
        "year": "2024",
        "photo": "https://images.unsplash.com/photo-1501196354995-cbb51c65aaea?w=500&q=80"
      },
      {
        "id": "res_6",
        "studentName": "Moumita Kar",
        "exam": "WBCS Group C (Commercial Tax Officer)",
        "rankResult": "Rank 29",
        "year": "2023",
        "photo": "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?w=500&q=80"
      }
    ]
    """.trimIndent()

    val BANNERS_JSON = """
    [
      {
        "id": "ban_1",
        "title": "Admissions Open for WBCS 2026-27",
        "subtitle": "Join Memari's Premier Coaching Institute with 100+ Selections",
        "imageUrl": "https://images.unsplash.com/photo-1523240795612-9a054b0db644?w=1200&q=80",
        "actionUrl": "courses"
      },
      {
        "id": "ban_2",
        "title": "Mission SSC CGL & CHSL 2026",
        "subtitle": "Special Shortcut Math & Reasoning Mastery Batches",
        "imageUrl": "https://images.unsplash.com/photo-1434030216411-0b793f4b4173?w=1200&q=80",
        "actionUrl": "courses"
      },
      {
        "id": "ban_3",
        "title": "WB Police SI & Constable Special",
        "subtitle": "Complete Written + Physical PMT Guidance by Experts",
        "imageUrl": "https://images.unsplash.com/photo-1544717305-2782549b5136?w=1200&q=80",
        "actionUrl": "courses"
      },
      {
        "id": "ban_4",
        "title": "Free Demo Classes Every Weekend",
        "subtitle": "Visit Malancha Complex, Chakdighi Road, Memari",
        "imageUrl": "https://images.unsplash.com/photo-1524178232363-1fb2b075b655?w=1200&q=80",
        "actionUrl": "contact"
      }
    ]
    """.trimIndent()

    val GALLERY_JSON = """
    [
      {
        "id": "gal_1",
        "title": "Spacious AC Classroom Lecture",
        "category": "Classrooms",
        "imageUrl": "https://images.unsplash.com/photo-1524178232363-1fb2b075b655?w=1000&q=80"
      },
      {
        "id": "gal_2",
        "title": "Weekly OMR Mock Examination Hall",
        "category": "Examinations",
        "imageUrl": "https://images.unsplash.com/photo-1434030216411-0b793f4b4173?w=1000&q=80"
      },
      {
        "id": "gal_3",
        "title": "Study Library & Aspirants Reading Hall",
        "category": "Library",
        "imageUrl": "https://images.unsplash.com/photo-1497633762265-9d179a990aa6?w=1000&q=80"
      },
      {
        "id": "gal_4",
        "title": "Annual Toppers Felicitation Ceremony",
        "category": "Events",
        "imageUrl": "https://images.unsplash.com/photo-1511578314322-379afb476865?w=1000&q=80"
      },
      {
        "id": "gal_5",
        "title": "One-on-One Faculty Doubt Solving Session",
        "category": "Mentorship",
        "imageUrl": "https://images.unsplash.com/photo-1531497865144-0464ef8fb9a9?w=1000&q=80"
      },
      {
        "id": "gal_6",
        "title": "Career Counseling & Civil Service Seminar",
        "category": "Seminars",
        "imageUrl": "https://images.unsplash.com/photo-1475721027785-f74eccf877e2?w=1000&q=80"
      }
    ]
    """.trimIndent()

    val SETTINGS_JSON = """
    {
      "instituteName": "MEDHA MANTRA Competitive Institute",
      "tagline": "EMPOWERING MINDS, CREATING FUTURES",
      "website": "https://medhamantra.com",
      "phone": "6297034796",
      "whatsapp": "6297034796",
      "email": "medhamantra.memari@gmail.com",
      "address": "1st Floor, Malancha Complex, Chakdighi Road, Near Abhijan Sangha Club, Memari, Purba Bardhaman 713146",
      "mapUrl": "https://maps.google.com/?q=Malancha+Complex+Chakdighi+Road+Memari+Purba+Bardhaman+713146",
      "facebook": "https://facebook.com/medhamantra",
      "youtube": "https://youtube.com/@medhamantra",
      "telegram": "https://t.me/medhamantra",
      "aboutText": "MEDHA MANTRA Competitive Institute is a premier institution headquartered in Memari, Purba Bardhaman, specializing in result-oriented coaching for West Bengal and All-India competitive examinations including WBCS, SSC, Railways, Banking, and Police Services. Our student-centric methodology, seasoned faculty, exhaustive study modules, and rigorous test analysis empower every aspirant to realize their dream of a prestigious government career.",
      "features": [
        "Specialist Faculty with 10+ Years Experience",
        "Comprehensive Exam-Oriented Study Material",
        "Weekly Speed Tests with Real OMR Sheet Practice",
        "Individual Mentorship & Personalized Doubt Desks",
        "Fully Air-Conditioned Classrooms with Digital Aids",
        "Well-Stocked Competitive Library and Reading Room"
      ]
    }
    """.trimIndent()
}
