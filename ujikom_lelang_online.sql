-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 13 Apr 2020 pada 09.49
-- Versi server: 10.4.8-MariaDB
-- Versi PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ujikom_lelang_online`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barangs`
--

CREATE TABLE `barangs` (
  `id_barang` bigint(20) UNSIGNED NOT NULL,
  `nama_barang` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tgl` date NOT NULL,
  `harga_awal` int(11) NOT NULL,
  `deskripsi_barang` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `barangs`
--

INSERT INTO `barangs` (`id_barang`, `nama_barang`, `tgl`, `harga_awal`, `deskripsi_barang`, `created_at`, `updated_at`) VALUES
(1, 'tv', '1212-12-12', 12000, 'Energistically create error-free relationships whereas empowered meta-services. Appropriately integrate tactical deliverables rather than competitive testing procedures. Progressively iterate intermandated web services and focused solutions. Proactively revolutionize cross-unit supply chains without value-added functionalities. Dramatically scale dynamic action items with cross functional results.\r\n\r\nEnergistically deploy 2.0 mindshare before pandemic meta-services. Intrinsicly actualize intermandated action items with value-added applications. Dramatically embrace team building strategic theme areas for cutting-edge information. Seamlessly supply bleeding-edge sources and frictionless functionalities. Progressively revolutionize go forward content via innovative information.\r\n\r\nEfficiently aggregate one-to-one leadership skills through viral schemas. Interactively productize top-line e-business and resource-leveling schemas. Credibly target stand-alone platforms vis-a-vis clicks-and-mortar vortals. Continually underwhelm turnkey technology vis-a-vis economically sound paradigms. Credibly communicate robust best practices without just in time leadership.\r\n\r\nObjectively evisculate principle-centered processes vis-a-vis innovative value. Rapidiously enhance world-class synergy and equity invested ROI. Efficiently conceptualize global infrastructures whereas synergistic experiences. Assertively foster market-driven collaboration and idea-sharing before user friendly outsourcing. Uniquely extend adaptive imperatives through granular outsourcing.\r\n\r\nContinually recaptiualize next-generation experiences rather than cross-platform deliverables. Holisticly impact client-centered growth strategies and state of the art results. Globally engineer interactive testing procedures through extensible vortals. Phosfluorescently incentivize highly efficient technology after empowered quality vectors. Efficiently implement bleeding-edge methods of empowerment after state of the art ideas.\r\n\r\nContinually pursue visionary total linkage through world-class models. Compellingly procrastinate fully researched outsourcing vis-a-vis holistic e-markets. Proactively reintermediate impactful platforms through equity invested value. Competently streamline goal-oriented mindshare without virtual systems. Professionally whiteboard client-centered benefits through premium infrastructures.\r\n\r\nGlobally morph cross-platform experiences without clicks-and-mortar users. Quickly strategize covalent potentialities and granular intellectual capital. Conveniently redefine web-enabled technologies via viral platforms. Seamlessly synergize bricks-and-clicks.\n\nhaha', '2020-03-18 21:57:11', '2020-04-02 02:51:57'),
(2, 'admin', '2000-12-20', 12000, 'bagus', '2020-03-18 22:01:57', '2020-03-20 06:08:39'),
(3, 'Admin', '0000-00-00', 12000, 'bagus banged', '2020-03-18 22:05:25', '2020-03-18 22:11:31'),
(4, 'Hanif', '2002-03-20', 545494, 'hanif', '2020-03-18 22:27:55', '2020-03-18 22:27:55'),
(5, 'Kulkas', '2020-03-21', 120000, 'bagus', '2020-03-21 05:10:07', '2020-03-21 05:10:07');

-- --------------------------------------------------------

--
-- Struktur dari tabel `histories`
--

CREATE TABLE `histories` (
  `id_history` bigint(20) UNSIGNED NOT NULL,
  `id_lelang` bigint(20) UNSIGNED DEFAULT NULL,
  `id_barang` bigint(20) UNSIGNED DEFAULT NULL,
  `id_masyarakat` bigint(20) UNSIGNED DEFAULT NULL,
  `penawaran_harga` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `histories`
--

INSERT INTO `histories` (`id_history`, `id_lelang`, `id_barang`, `id_masyarakat`, `penawaran_harga`, `created_at`, `updated_at`) VALUES
(1, 2, 2, NULL, 0, '2020-03-20 06:06:25', '2020-03-20 06:06:25'),
(2, 3, 3, NULL, 0, '2020-03-20 06:06:30', '2020-03-20 06:06:30'),
(3, 1, 1, NULL, 0, '2020-03-20 06:06:35', '2020-03-20 06:06:35');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategoris`
--

CREATE TABLE `kategoris` (
  `id_kategori` bigint(20) UNSIGNED NOT NULL,
  `kategori` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `lelangs`
--

CREATE TABLE `lelangs` (
  `id_lelang` bigint(20) UNSIGNED NOT NULL,
  `id_barang` bigint(20) UNSIGNED DEFAULT NULL,
  `nama_barang` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tgl_lelang` date NOT NULL,
  `harga_akhir` int(11) DEFAULT NULL,
  `id_masyarakat` bigint(20) UNSIGNED DEFAULT NULL,
  `id_petugas` bigint(20) UNSIGNED DEFAULT NULL,
  `status` enum('dibuka','ditutup') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `lelangs`
--

INSERT INTO `lelangs` (`id_lelang`, `id_barang`, `nama_barang`, `tgl_lelang`, `harga_akhir`, `id_masyarakat`, `id_petugas`, `status`, `created_at`, `updated_at`) VALUES
(1, 1, NULL, '2020-03-19', 0, NULL, NULL, 'ditutup', '2020-03-18 22:25:52', '2020-03-20 06:06:35'),
(2, 2, NULL, '2020-03-19', 200000, NULL, NULL, 'dibuka', '2020-03-18 22:26:20', '2020-03-21 05:55:37'),
(3, 3, NULL, '2020-03-19', 0, NULL, NULL, 'ditutup', '2020-03-18 22:27:36', '2020-03-20 06:06:30'),
(4, 4, NULL, '2020-03-20', 0, NULL, NULL, 'dibuka', '2020-03-20 06:11:08', '2020-03-20 18:46:49'),
(5, 5, NULL, '2020-03-21', 0, NULL, NULL, 'ditutup', '2020-03-21 05:10:26', '2020-03-21 05:10:26');

-- --------------------------------------------------------

--
-- Struktur dari tabel `levels`
--

CREATE TABLE `levels` (
  `id_level` bigint(20) UNSIGNED NOT NULL,
  `level` enum('administrator','petugas','masyarakat') COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `levels`
--

INSERT INTO `levels` (`id_level`, `level`, `created_at`, `updated_at`) VALUES
(1, 'administrator', NULL, NULL),
(2, 'petugas', NULL, NULL),
(3, 'masyarakat', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `masyarakats`
--

CREATE TABLE `masyarakats` (
  `id_masyarakat` bigint(20) UNSIGNED NOT NULL,
  `nama_lengkap` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telp` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_user` bigint(20) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `masyarakats`
--

INSERT INTO `masyarakats` (`id_masyarakat`, `nama_lengkap`, `telp`, `id_user`, `created_at`, `updated_at`) VALUES
(1, 'hanif', '087654456789', 4, '2020-03-21 10:10:46', '2020-03-21 10:10:46'),
(2, 'hanif amrullah', '087829024030', 5, '2020-03-21 10:47:37', '2020-03-21 10:47:37');

-- --------------------------------------------------------

--
-- Struktur dari tabel `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(9, '2013_02_23_085402_create_levels_table', 1),
(10, '2014_10_12_000000_create_users_table', 1),
(11, '2020_02_23_085332_create_masyarakats_table', 1),
(12, '2020_02_23_085400_create_kategoris_table', 1),
(13, '2020_02_23_085401_create_barangs_table', 1),
(14, '2020_02_23_085403_create_petugas_table', 1),
(15, '2020_02_23_085427_create_lelangs_table', 1),
(16, '2020_02_23_085444_create_histories_table', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `petugas`
--

CREATE TABLE `petugas` (
  `id_petugas` bigint(20) UNSIGNED NOT NULL,
  `nama_petugas` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_user` bigint(20) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `petugas`
--

INSERT INTO `petugas` (`id_petugas`, `nama_petugas`, `id_user`, `created_at`, `updated_at`) VALUES
(1, 'hanif', 3, '2020-03-18 21:23:39', '2020-03-18 21:23:39');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id_user` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_level` bigint(20) UNSIGNED DEFAULT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `id_level`, `remember_token`, `created_at`, `updated_at`) VALUES
(2, 'admin', '$2y$10$El1icpVs.7vR.7tjZeXvIO4pRIzBa12oxwdqPWRrjbNUl2GyQULNe', 1, '3S5AtPw', '2020-03-18 21:23:22', '2020-03-18 21:23:22'),
(3, 'admin', '$2y$10$842m/37F/i8FTyIAR/ju4OjxkriY/VGBe1B5izy2kRVnsJeg5PmAO', 1, 'MKwaMOI', '2020-03-18 21:23:39', '2020-03-18 21:23:39'),
(4, 'masyarakat', '$2y$10$EVGD3tS.rhOnUMo7gHxHSOVz1NmlBFgM17II92oYVdtTxAy7ecQK6', 3, 'AWNSeou', '2020-03-21 10:10:45', '2020-03-21 10:10:45'),
(5, 'hanif', '$2y$10$TsBbw/OxF6PmhrvI82LI2eUEeBS9SjQboxRizWvS0luq0nbR23gx.', 3, 'ufS8Gmk', '2020-03-21 10:47:37', '2020-03-21 10:47:37');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barangs`
--
ALTER TABLE `barangs`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indeks untuk tabel `histories`
--
ALTER TABLE `histories`
  ADD PRIMARY KEY (`id_history`),
  ADD KEY `histories_id_lelang_index` (`id_lelang`),
  ADD KEY `histories_id_barang_index` (`id_barang`),
  ADD KEY `histories_id_masyarakat_index` (`id_masyarakat`);

--
-- Indeks untuk tabel `kategoris`
--
ALTER TABLE `kategoris`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indeks untuk tabel `lelangs`
--
ALTER TABLE `lelangs`
  ADD PRIMARY KEY (`id_lelang`),
  ADD KEY `lelangs_id_barang_index` (`id_barang`),
  ADD KEY `lelangs_id_masyarakat_index` (`id_masyarakat`),
  ADD KEY `lelangs_id_petugas_index` (`id_petugas`);

--
-- Indeks untuk tabel `levels`
--
ALTER TABLE `levels`
  ADD PRIMARY KEY (`id_level`);

--
-- Indeks untuk tabel `masyarakats`
--
ALTER TABLE `masyarakats`
  ADD PRIMARY KEY (`id_masyarakat`),
  ADD KEY `masyarakats_id_user_index` (`id_user`);

--
-- Indeks untuk tabel `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id_petugas`),
  ADD KEY `petugas_id_user_index` (`id_user`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `users_id_level_index` (`id_level`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `barangs`
--
ALTER TABLE `barangs`
  MODIFY `id_barang` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `histories`
--
ALTER TABLE `histories`
  MODIFY `id_history` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `kategoris`
--
ALTER TABLE `kategoris`
  MODIFY `id_kategori` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `lelangs`
--
ALTER TABLE `lelangs`
  MODIFY `id_lelang` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `levels`
--
ALTER TABLE `levels`
  MODIFY `id_level` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `masyarakats`
--
ALTER TABLE `masyarakats`
  MODIFY `id_masyarakat` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT untuk tabel `petugas`
--
ALTER TABLE `petugas`
  MODIFY `id_petugas` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id_user` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `histories`
--
ALTER TABLE `histories`
  ADD CONSTRAINT `histories_id_barang_foreign` FOREIGN KEY (`id_barang`) REFERENCES `barangs` (`id_barang`) ON DELETE CASCADE,
  ADD CONSTRAINT `histories_id_lelang_foreign` FOREIGN KEY (`id_lelang`) REFERENCES `lelangs` (`id_lelang`) ON DELETE CASCADE,
  ADD CONSTRAINT `histories_id_masyarakat_foreign` FOREIGN KEY (`id_masyarakat`) REFERENCES `masyarakats` (`id_masyarakat`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `lelangs`
--
ALTER TABLE `lelangs`
  ADD CONSTRAINT `lelangs_id_barang_foreign` FOREIGN KEY (`id_barang`) REFERENCES `barangs` (`id_barang`) ON DELETE CASCADE,
  ADD CONSTRAINT `lelangs_id_masyarakat_foreign` FOREIGN KEY (`id_masyarakat`) REFERENCES `masyarakats` (`id_masyarakat`) ON DELETE CASCADE,
  ADD CONSTRAINT `lelangs_id_petugas_foreign` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `masyarakats`
--
ALTER TABLE `masyarakats`
  ADD CONSTRAINT `masyarakats_id_user_foreign` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `petugas`
--
ALTER TABLE `petugas`
  ADD CONSTRAINT `petugas_id_user_foreign` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_id_level_foreign` FOREIGN KEY (`id_level`) REFERENCES `levels` (`id_level`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
