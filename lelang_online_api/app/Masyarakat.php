<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\Lelang;
use App\History;

class Masyarakat extends Model
{
    protected $guarded = ['id_masyarakat'];
    protected $primaryKey = 'id_masyarakat';

    public function lelangs()
    {
        return $this->hasMany(Lelang::class);
    }

    public function history()
    {
        return $this->hasMany(History::class);
    }
}
